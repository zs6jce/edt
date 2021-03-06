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

import org.eclipse.edt.compiler.core.ast.Node;
import org.eclipse.edt.compiler.internal.core.builder.IProblemRequestor;
import org.eclipse.edt.compiler.internal.core.lookup.ICompilerOptions;
import org.eclipse.edt.mof.egl.Element;
import org.eclipse.edt.mof.egl.Part;


/**
 * @author svihovec
 */
public abstract class InvocationValidationRule extends AbstractValidationRule {

	public InvocationValidationRule(String caseSensitiveInternedName) {
		super(caseSensitiveInternedName);
	}
	
	public abstract void validate(Node node, Element element, Part declaringPart, IProblemRequestor problemRequestor, ICompilerOptions compilerOptions);
}
