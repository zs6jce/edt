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
package org.eclipse.edt.compiler.internal.core.validation.annotation;

import org.eclipse.edt.compiler.core.ast.Expression;
import org.eclipse.edt.compiler.internal.core.builder.IProblemRequestor;
import org.eclipse.edt.compiler.internal.core.lookup.ICompilerOptions;
import org.eclipse.edt.mof.egl.Member;


/**
 * @author demurray
 */
public interface IFieldAccessAnnotationValidationRule {

	boolean validateLValue(Expression lValue, Member fieldBinding, IProblemRequestor problemRequestor, ICompilerOptions compilerOptions);
	boolean validateRValue(Expression rValue, Member fieldBinding, IProblemRequestor problemRequestor, ICompilerOptions compilerOptions);
}
