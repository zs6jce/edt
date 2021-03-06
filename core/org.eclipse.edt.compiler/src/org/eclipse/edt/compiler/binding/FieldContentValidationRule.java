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
package org.eclipse.edt.compiler.binding;

import java.util.Map;

import org.eclipse.edt.compiler.core.ast.FunctionParameter;
import org.eclipse.edt.compiler.core.ast.Node;
import org.eclipse.edt.compiler.core.ast.Type;
import org.eclipse.edt.compiler.internal.core.builder.IProblemRequestor;
import org.eclipse.edt.compiler.internal.core.lookup.ICompilerOptions;
import org.eclipse.edt.mof.egl.Annotation;
import org.eclipse.edt.mof.egl.Member;


/**
 * @author svihovec
 *
 */
public abstract class FieldContentValidationRule extends ContentValidationRule {

	public FieldContentValidationRule(String caseSensitiveInternedName) {
		super(caseSensitiveInternedName);
	}
	
	public abstract void validate(Node errorNode, Node target, Member containerBinding, String canonicalContainerName, Map<String, Annotation> allAnnotations, IProblemRequestor problemRequestor, ICompilerOptions compilerOptions);
	
	public void validateFunctionParameter(FunctionParameter fParameter, Member parameterBinding, IProblemRequestor problemRequestor, ICompilerOptions compilerOptions) {		
	}
	
	public void validateFunctionReturnType(Type typeNode, org.eclipse.edt.mof.egl.Type typeBinding, Member declaringMember, IProblemRequestor problemRequestor, ICompilerOptions compilerOptions) {		
	}
}
