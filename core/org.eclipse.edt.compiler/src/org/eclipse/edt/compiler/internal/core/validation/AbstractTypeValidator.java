/*******************************************************************************
 * Copyright © 2012, 2013 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *
 *******************************************************************************/
package org.eclipse.edt.compiler.internal.core.validation;

import org.eclipse.edt.compiler.TypeValidator;
import org.eclipse.edt.compiler.binding.IPartBinding;
import org.eclipse.edt.compiler.core.ast.AbstractASTVisitor;
import org.eclipse.edt.compiler.core.ast.Node;
import org.eclipse.edt.compiler.core.ast.Type;
import org.eclipse.edt.compiler.internal.core.builder.IProblemRequestor;
import org.eclipse.edt.compiler.internal.core.lookup.ICompilerOptions;

public abstract class AbstractTypeValidator extends AbstractASTVisitor implements TypeValidator {

	protected IProblemRequestor problemRequestor;
	protected ICompilerOptions compilerOptions;
	protected IPartBinding declaringPart;
	
	@Override
	public void validate(Node node, IPartBinding declaringPart, IProblemRequestor problemRequestor, ICompilerOptions compilerOptions) {
		if (node instanceof Type) {
			validateType((Type)node, declaringPart, problemRequestor, compilerOptions);
		}
	}
	
	@Override
	public void validateType(Type type, IPartBinding declaringPart, IProblemRequestor problemRequestor, ICompilerOptions compilerOptions) {
		this.problemRequestor = problemRequestor;
		this.compilerOptions = compilerOptions;
		this.declaringPart = declaringPart;
		type.accept(this);
	}

}
