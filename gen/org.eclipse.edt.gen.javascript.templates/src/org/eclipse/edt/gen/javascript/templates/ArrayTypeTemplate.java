/*******************************************************************************
 * Copyright © 2011, 2013 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *
 *******************************************************************************/
package org.eclipse.edt.gen.javascript.templates;

import java.util.List;

import org.eclipse.edt.gen.javascript.CommonUtilities;
import org.eclipse.edt.gen.javascript.Constants;
import org.eclipse.edt.gen.javascript.Context;
import org.eclipse.edt.mof.codegen.api.TabbedWriter;
import org.eclipse.edt.mof.egl.*;

public class ArrayTypeTemplate extends JavaScriptTemplate {

	public Boolean isAssignmentBreakupWanted(ArrayType type, Context ctx, Assignment expr) {
		// types can override this to cause an compound assignment expression to be broken up
		// the arg contains the operation being asked about
		if (expr.getLHS() instanceof MemberAccess && CommonUtilities.isRUIWidget(expr.getLHS().getQualifier().getType())){
			return false;
		}
		return true;  ///TODO sbg This should be true but it breaks onChange ::= some_delegate
	}
	
	public Boolean isListReorganizationWanted(Type type, Context ctx, Expression expr) {
		// types can override this to cause list reorganization to be done
		if(expr instanceof Assignment && ((Assignment)expr).getLHS() instanceof MemberAccess && CommonUtilities.isRUIWidget(((Assignment)expr).getLHS().getQualifier().getType())){
			return false;
		}
		return true;
	}

	public void genDefaultValue(ArrayType generic, Context ctx, TabbedWriter out, Field arg) {
		if (arg.isNullable()) 
			out.print("null");
		else
			processDefaultValue(generic, ctx, out);
	}

	public void genDefaultValue(ArrayType generic, Context ctx, TabbedWriter out) {
		processDefaultValue(generic, ctx, out);
	}

	public void processDefaultValue(ArrayType generic, Context ctx, TabbedWriter out) {
		out.print(" []");
	}
	
	public void genContainerBasedNewExpression( ArrayType type, Context ctx, TabbedWriter out, NewExpression expr ) {
		List<Expression> arguments = expr.getArguments();
		genContainerBasedNewExpressionArguments( type, ctx, out, arguments, 0 );
	}
	
	private void genContainerBasedNewExpressionArguments( ArrayType type, Context ctx, TabbedWriter out, List<Expression> arguments, int next ) {
		// If the size isn't a literal or parameter to the function, pass it
		// in to the function we generate.
		Expression arraySize = null;
		String sizeVar = "";
		if ( arguments.size() > 0 )
		{
			arraySize = arguments.get( next );
			if ( !(arraySize instanceof NumericLiteral
				|| (arraySize instanceof MemberName && ((MemberName)arraySize).getMember() instanceof FunctionParameter)) )
			{
				sizeVar = ctx.nextTempName();
			}
		}

		ArrayType generic = (ArrayType)type;
		String temporary = ctx.nextTempName();
		out.print("(function(" + sizeVar + ") { var ");
		out.print(temporary);
		out.print(" = []; ");
		out.print(temporary);
		out.print(".setType(");
		out.print("\"");
		genSignature(generic, ctx, out);
		out.print("\"");
		out.println(");");
		out.print("for (var i = 0; i < ");
		if ( sizeVar.length() > 0 )
		{
			out.print( sizeVar );
		}
		else if ( arraySize != null )
		{
			ctx.invoke( genExpression, arraySize, ctx, out );
		}
		else
		{
			out.print( '0' );
		}
		out.println("; i++) {");
		out.print(temporary);
		out.print("[i] = ");
		if (generic.elementsNullable())
			out.print("null");
		else {
			//arguments are the dimensions' size, next is the current dimension
			if ( next < arguments.size() - 1 ) {
				genContainerBasedNewExpressionArguments( type, ctx, out, arguments, next + 1 );
			} else {
				ctx.invoke(genDefaultValue, getArrayElementType(generic), ctx, out);
			}
		}
		out.println(";}");
		out.print("return ");
		out.print(temporary);
		out.print(";})(");
		if ( sizeVar.length() > 0 )
		{
			ctx.invoke( genExpression, arraySize, ctx, out );
		}
		out.print(')');
	}
	
	private Type getArrayElementType(ArrayType generic){
		Type type = generic.getElementType();
		while(type instanceof ArrayType){
			type = ((ArrayType) type).getElementType();
		}
		return type;
	}

	public void genSignature(ArrayType generic, Context ctx, TabbedWriter out) {
		if (!generic.getTypeArguments().isEmpty()) {
			for (int i = 0; i < generic.getTypeArguments().size(); i++)
				out.print("[");
		}
		if (generic.elementsNullable())
			out.print("?");
		ctx.invoke(genSignature, generic.getElementType(), ctx, out);
	}

	public void genTypeBasedAssignment(Type type, Context ctx, TabbedWriter out, Assignment arg) {
		String operator = "=";
		if (arg.getOperator() != null && arg.getOperator().length() > 0)
			operator = arg.getOperator();
		if ("::=".equals(operator)) {
			ctx.putAttribute(arg.getLHS(), Constants.EXPR_LHS, Boolean.FALSE); // Ensure we don't use a getter for the
																				// accessor
			if (arg.getLHS() instanceof MemberAccess) {
				/* Ensure we don't use a getter for the accessor */
				ctx.putAttribute(((MemberAccess) arg.getLHS()).getNamedElement(), Constants.EXPR_LHS, Boolean.FALSE); 
			}
			ctx.invoke(genExpression, arg.getLHS(), ctx, out);
			
			Expression rhs = arg.getRHS();
			
			// Normally, the RHS will be encased in an AsExpression and we'll want to unwrap it....
			if (rhs instanceof AsExpression)
				rhs = ((AsExpression)rhs).getObjectExpr();
			
			if (rhs.getType() instanceof ArrayType)
				out.print(".appendAll(");
			else
				out.print(".appendElement(");
			if (rhs instanceof BoxingExpression)
				ctx.invoke(genExpression, ((BoxingExpression)rhs).getExpr(), ctx, out);
			else
				ctx.invoke(genExpression, rhs, ctx, out);
			out.print(")");
		} 
		else
			ctx.invokeSuper(this, genTypeBasedAssignment, type, ctx, out, arg);
	}

	public void genRuntimeTypeName(ArrayType generic, Context ctx, TabbedWriter out, TypeNameKind arg) {
		out.print("Array");
	}

	public void genFieldInfoTypeName(ArrayType generic, Context ctx, TabbedWriter out, TypeNameKind arg) {
		ctx.invoke(genFieldInfoTypeName, generic.getElementType(), ctx, out, TypeNameKind.JavascriptImplementation);
	}

	public void genConversionOperation(ArrayType type, Context ctx, TabbedWriter out, AsExpression arg) {
		Type etType = arg.getEType();
//		// If convert to any array type
//		if(etType instanceof ArrayType){
//			if(((ArrayType) etType).getElementType().getTypeSignature().equals("eglx.lang.EAny")){
//				ctx.invoke(genExpression, arg.getObjectExpr(), ctx, out);
//				return;
//			}				
//		}
		
		out.print("egl.convertAnyToArrayType(");
		ctx.put( "etType_for_array_conversion_" + arg.getObjectExpr(), etType );
		ctx.invoke(genExpression, arg.getObjectExpr(), ctx, out);
		ctx.remove( "etType_for_array_conversion_" + arg.getObjectExpr() );
		out.print(",\"");
		ctx.put(Constants.SubKey_isaSignature, "true");
		ctx.invoke(genSignature, etType, ctx, out, arg);
		ctx.remove(Constants.SubKey_isaSignature);
		out.print("\")");
	}
	public void genServiceCallbackArgType(ArrayType type, Context ctx, TabbedWriter out){
		ctx.invoke(genRuntimeTypeName, type.getElementType(), ctx, out, TypeNameKind.EGLImplementation);
	}
}
