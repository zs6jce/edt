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
package org.eclipse.edt.compiler.internal.core.validation.part;

import org.eclipse.edt.compiler.core.ast.AbstractASTVisitor;
import org.eclipse.edt.compiler.core.ast.FunctionParameter;
import org.eclipse.edt.compiler.core.ast.NestedFunction;
import org.eclipse.edt.compiler.internal.core.builder.IProblemRequestor;
import org.eclipse.edt.compiler.internal.core.validation.statement.ThrowStatementValidator;
import org.eclipse.edt.mof.egl.ArrayType;
import org.eclipse.edt.mof.egl.EGLClass;
import org.eclipse.edt.mof.egl.Field;
import org.eclipse.edt.mof.egl.Function;
import org.eclipse.edt.mof.egl.Handler;
import org.eclipse.edt.mof.egl.Record;
import org.eclipse.edt.mof.egl.Type;
import org.eclipse.edt.mof.egl.utils.TypeUtils;



/**
 * @author cduval
 *
 */
public class ServiceInterfaceValidatorUtil {

	public static void validateParametersAndReturn(final NestedFunction nestedFunction,final IProblemRequestor problemRequestor ) {
		if(!(nestedFunction.getName().resolveMember() instanceof Function)){
			return;
		}

		nestedFunction.accept(new AbstractASTVisitor(){
			public boolean visit (FunctionParameter functionParameter){
				
				if (functionParameter.isParmConst()) {
           			problemRequestor.acceptProblem(functionParameter,
	        				IProblemRequestor.SERVICE_PARM_CANNOT_BE_CONST, 
				              new String[] {
	        				functionParameter.getName().getCanonicalName(),
	        				nestedFunction.getName().getCanonicalName()});
				}
				
				Type typeBinding = functionParameter.getType().resolveType();
				if (typeBinding != null){

					if( !isTypeValidInServicesAndProxy( typeBinding ) ) {
						problemRequestor.acceptProblem(functionParameter.getType(),
								IProblemRequestor.TYPE_NOT_ALLOWED_IN_SERVICE_OR_PROXY_FUNC_PARM,
									new String[] {typeBinding.getTypeSignature()});

					}
					
					if( functionParameter.getName().resolveMember() instanceof Field)
						problemRequestor.acceptProblem(functionParameter,
								IProblemRequestor.FIELD_NOT_ALLOWED_IN_SERVICE_OR_PROXY_FUNC_PARM);

					}
				return false;
			}
			
			public void endVisit(NestedFunction nestedFunction){
				if (nestedFunction.hasReturnType()){
					Type typeBinding = nestedFunction.getReturnType().resolveType();
					if (typeBinding != null && !isTypeValidInServicesAndProxy( typeBinding )){
						problemRequestor.acceptProblem(nestedFunction.getReturnType(),
								IProblemRequestor.TYPE_NOT_ALLOWED_AS_SERVICE_OR_PROXY_FUNC_RETURN,
								new String[] {typeBinding.getTypeSignature()});
					}
					
				}
			}
		});		
	}

	public static boolean isTypeValidInServicesAndProxy(Type typeBinding){
		return (TypeUtils.isTextType(typeBinding) ||
				TypeUtils.isNumericType(typeBinding) ||
				(typeBinding != null && typeBinding.getClassifier() instanceof EGLClass &&
						(typeBinding.getClassifier().equals(TypeUtils.Type_DATE) ||
						typeBinding.getClassifier().equals(TypeUtils.Type_TIME) ||
						typeBinding.getClassifier().equals(TypeUtils.Type_TIMESTAMP) ||
						typeBinding.getClassifier().equals(TypeUtils.Type_BOOLEAN)))) ||
				(typeBinding instanceof ArrayType && isTypeValidInServicesAndProxy(((ArrayType)typeBinding).getElementType())) ||
				typeBinding instanceof Record ||
				typeBinding instanceof Handler ||
				(TypeUtils.isTypeOrSubtypeOf(typeBinding, ThrowStatementValidator.AnyExceptionMofKey));
	}
	
}
