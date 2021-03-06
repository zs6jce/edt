/*******************************************************************************
 * Copyright © 2013 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *
 *******************************************************************************/
package org.eclipse.edt.compiler.binding;

import java.math.BigDecimal;

import org.eclipse.edt.compiler.core.ast.AbstractASTExpressionVisitor;
import org.eclipse.edt.compiler.core.ast.AnnotationExpression;
import org.eclipse.edt.compiler.core.ast.ArrayAccess;
import org.eclipse.edt.compiler.core.ast.ArrayLiteral;
import org.eclipse.edt.compiler.core.ast.BinaryExpression;
import org.eclipse.edt.compiler.core.ast.BooleanLiteral;
import org.eclipse.edt.compiler.core.ast.BytesLiteral;
import org.eclipse.edt.compiler.core.ast.DecimalLiteral;
import org.eclipse.edt.compiler.core.ast.Expression;
import org.eclipse.edt.compiler.core.ast.FloatLiteral;
import org.eclipse.edt.compiler.core.ast.FunctionInvocation;
import org.eclipse.edt.compiler.core.ast.IntegerLiteral;
import org.eclipse.edt.compiler.core.ast.QualifiedName;
import org.eclipse.edt.compiler.core.ast.SQLLiteral;
import org.eclipse.edt.compiler.core.ast.SetValuesExpression;
import org.eclipse.edt.compiler.core.ast.SimpleName;
import org.eclipse.edt.compiler.core.ast.StringLiteral;
import org.eclipse.edt.compiler.core.ast.TypeLiteralExpression;
import org.eclipse.edt.compiler.core.ast.UnaryExpression;
import org.eclipse.edt.compiler.internal.core.builder.IProblemRequestor;
import org.eclipse.edt.compiler.internal.core.dependency.IDependencyRequestor;
import org.eclipse.edt.compiler.internal.core.lookup.AnnotationLeftHandScope;
import org.eclipse.edt.compiler.internal.core.lookup.DefaultBinder;
import org.eclipse.edt.compiler.internal.core.lookup.ICompilerOptions;
import org.eclipse.edt.compiler.internal.core.lookup.Scope;
import org.eclipse.edt.mof.egl.Annotation;
import org.eclipse.edt.mof.egl.AnnotationType;
import org.eclipse.edt.mof.egl.IrFactory;
import org.eclipse.edt.mof.egl.Part;
import org.eclipse.edt.mof.utils.EList;

public class AnnotationValueGatherer extends DefaultBinder{
	Expression expr;
	Object value;
	boolean isNegative;


	public AnnotationValueGatherer(Expression expr, Scope currentScope, Part currentBinding, IDependencyRequestor dependencyRequestor, IProblemRequestor problemRequestor, ICompilerOptions compilerOptions) {
		super(currentScope, currentBinding, dependencyRequestor, problemRequestor, compilerOptions);
		this.expr = expr;
	}
	
	public Object getValue() {
		expr.accept(this);
		return value;
	}
		
	public void endVisit(SimpleName simpleName) {
		if (simpleName.resolveElement() != null) {
			value = simpleName.resolveElement();
		}
		else {
			value = simpleName.resolveType();
		}
	}

	public void endVisit(QualifiedName qualifiedName) {
		if (qualifiedName.resolveElement() != null) {
			value = qualifiedName.resolveElement();
		}
		else {
			value = qualifiedName.resolveType();
		}
	}

	public void endVisit(ArrayAccess arrayAccess) {
		value = arrayAccess.resolveMember();
	}

	public boolean visit(IntegerLiteral integerLiteral) {
		String str = integerLiteral.getValue();
		if (isNegative) {
			str = "-" + str;
		}
		
		try {
			value = new Integer(str);
		} catch (NumberFormatException e) {
			problemRequestor.acceptProblem(integerLiteral, IProblemRequestor.INTEGER_LITERAL_OUT_OF_RANGE, new String[] { str });
			value = null;
		}
		integerLiteral.setBindAttempted(true);
		return false;
	}

	public boolean visit(FloatLiteral floatLiteral) {
		String str = floatLiteral.getValue();
		if (isNegative) {
			str = "-" + str;
		}
		value = new Double(str);
		floatLiteral.setBindAttempted(true);
		return false;
	}

	public boolean visit(DecimalLiteral decimalLiteral) {
		String str = decimalLiteral.getValue();
		if (isNegative) {
			str = "-" + str;
		}
		value = new BigDecimal(str);
		decimalLiteral.setBindAttempted(true);
		return false;
	}

	public boolean visit(StringLiteral stringLiteral) {
		value = stringLiteral.getValue();
		stringLiteral.setBindAttempted(true);
		return false;
	}
	
	private static class BinaryExpressionVisitor extends AbstractASTExpressionVisitor {
		private StringBuffer concatenatedString;
		private IProblemRequestor problemRequestor;
		private boolean exprValid = true;
		
		public BinaryExpressionVisitor(IProblemRequestor problemRequestor) {
			concatenatedString = new StringBuffer();
			this.problemRequestor = problemRequestor;
		}
		
		public String getConcatenatedString() {
			return concatenatedString.toString();
		}
		
		public boolean visit(BinaryExpression binaryExpression) {
			if(binaryExpression.getOperator() == BinaryExpression.Operator.PLUS || 
			   binaryExpression.getOperator() == BinaryExpression.Operator.CONCAT) {
				
				BinaryExpressionVisitor operand1Visitor = new BinaryExpressionVisitor(problemRequestor);
				binaryExpression.getFirstExpression().accept(operand1Visitor);
				concatenatedString.append(operand1Visitor.getConcatenatedString());
				
				BinaryExpressionVisitor operand2Visitor = new BinaryExpressionVisitor(problemRequestor);
				binaryExpression.getSecondExpression().accept(operand2Visitor);
				concatenatedString.append(operand2Visitor.getConcatenatedString());
				
				if(!operand1Visitor.exprValid || !operand2Visitor.exprValid) {
					problemRequestor.acceptProblem(binaryExpression, IProblemRequestor.INVALID_EXPRESSION_DATA_ACCESS_OR_STRING_LITERAL);
				}
			}
			else {
				exprValid = false;
			}
			binaryExpression.setBindAttempted(true);
			return false;
		}
		
		public boolean visit(StringLiteral stringLiteral) {
			if (stringLiteral.isHex()) {
				concatenatedString.append(convertUnicodeHexToString(stringLiteral.getValue()));
			}
			else {
				concatenatedString.append(stringLiteral.getValue());
			}
			stringLiteral.setBindAttempted(true);
			return false;
		}
		
		private String convertUnicodeHexToString(String str) {
			try {
				int charLen = (str.length()) / 4;
				if (charLen * 4 != str.length()) {
					charLen = charLen + 1;
				}
				
				char[] chars = new char[charLen];
				
				for (int i = 0; i < str.length(); i = i + 4) {
					String nextCharString;
					if (i + 4 < str.length()) {
						nextCharString = str.substring(i, i + 4);
					}
					else {
						nextCharString = str.substring(i);
					}
					
					char myUnicodeCharacter = (char) Integer.parseInt(nextCharString, 16);
					chars[i/4] = myUnicodeCharacter;
				}
				return new String(chars);
			} catch (NumberFormatException e) {
				return str;
			}

		}
		
		public boolean visitExpression(Expression expression) {
			exprValid = false;
			expression.setBindAttempted(true);
			return false;
		}			
	}
	
	public void endVisit(BinaryExpression binaryExpression) {
		super.endVisit(binaryExpression);
		
		BinaryExpressionVisitor subVisitor = new BinaryExpressionVisitor(problemRequestor);
		binaryExpression.accept(subVisitor);
		
		if(!subVisitor.exprValid) {
			problemRequestor.acceptProblem(binaryExpression, IProblemRequestor.INVALID_EXPRESSION_DATA_ACCESS_OR_STRING_LITERAL);
		}
		else {
			value = subVisitor.getConcatenatedString();
		}
	}

	public boolean visit(org.eclipse.edt.compiler.core.ast.NullLiteral nullLiteral) {
		value = IrFactory.INSTANCE.createNullLiteral();
		nullLiteral.setBindAttempted(true);
		return false;
	}

	public boolean visit(BooleanLiteral booleanLiteral) {
		value = new Boolean(booleanLiteral.booleanValue() == org.eclipse.edt.compiler.core.Boolean.YES);
		booleanLiteral.setBindAttempted(true);
		return false;
	}
	
	public boolean visit(BytesLiteral bytesLiteral) {
		value = bytesLiteral.getValue();
		bytesLiteral.setBindAttempted(true);
		return false;
	}

	public boolean visit(SQLLiteral sQLLiteral) {
		value = sQLLiteral.getValue();
		sQLLiteral.setBindAttempted(true);
		return false;
	}

	public boolean visit(TypeLiteralExpression typeLiteralExpression) {
		super.visit(typeLiteralExpression);
		value = typeLiteralExpression.getType().resolveType();
		typeLiteralExpression.setBindAttempted(true);
		return false;
	}

	public boolean visit(SetValuesExpression setValuesExpression) {
		
		Expression expr = setValuesExpression.getExpression();
		if (expr instanceof AnnotationExpression) {
			AnnotationExpression annotationExpression = (AnnotationExpression) expr;
			Annotation ann = getAnnotation(annotationExpression, problemRequestor);
			if (ann != null) {
				AnnotationLeftHandScope scope = new AnnotationLeftHandScope(currentScope, ann, (AnnotationType)ann.getEClass(), ann);
				SettingsBlockAnnotationBindingsCompletor completor = new SettingsBlockAnnotationBindingsCompletor(currentScope, currentBinding, scope, dependencyRequestor, problemRequestor, compilerOptions);
				setValuesExpression.getSettingsBlock().accept(completor);
				value = ann;
			}
		}
		else {
			problemRequestor.acceptProblem(setValuesExpression, IProblemRequestor.INVALID_EXPRESSION_DATA_ACCESS_OR_STRING_LITERAL);
			setValuesExpression.getExpression().setBindAttempted(true);
			setBindAttemptedForNames(setValuesExpression.getSettingsBlock());
		}
		
		return false;
		
	}
	
	public boolean visit(ArrayLiteral arrayLiteral) {
		EList<Object> entries = new EList<Object>();
		for (Expression expr : arrayLiteral.getExpressions()) {
			AnnotationValueGatherer gatherer = getGatherer(expr);
			if (gatherer.getValue() != null) {
				entries.add(gatherer.getValue());
			}
		}

		value = entries;
		arrayLiteral.setBindAttempted(true);
		return false;
	}
	
	AnnotationValueGatherer getGatherer(Expression expr) {
		return new AnnotationValueGatherer(expr, currentScope, currentBinding, dependencyRequestor, problemRequestor, compilerOptions);
	}

	public boolean visit(UnaryExpression unaryExpression) {
		if (unaryExpression.getOperator() == UnaryExpression.Operator.MINUS) {
			isNegative = !isNegative;
		}
		return true;
	}
	
	public boolean visit(FunctionInvocation functionInvocation) {
		super.visit(functionInvocation);
		value = functionInvocation.getTarget().resolveMember();
		return false;
	}

}
