/*******************************************************************************
 * Copyright © 2000, 2013 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *
 *******************************************************************************/
package org.eclipse.edt.ide.ui.editor;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.edt.ide.ui.internal.quickfix.IInvocationContext;

public interface IQuickFixProcessor {

	/**
	 * Returns <code>true</code> if the processor has proposals for the given problem. This test should be an
	 * optimistic guess and be very cheap.
	 *
	 * @param unit the compilation unit
	 * @param problemId the problem Id. The id is of a problem of the problem type(s) this processor specified in
	 * the extension point.
	 * @return <code>true</code> if the processor has proposals for the given problem
	 */
	boolean hasCorrections(int problemId);

	/**
	 * Collects corrections or code manipulations for the given context.
	 *
	 * @param context Defines current compilation unit, position and a shared AST
	 * @param locations Problems are the current location.
	 * @return the corrections applicable at the location or <code>null</code> if no proposals
	 * 			can be offered
	 * @throws CoreException CoreException can be thrown if the operation fails
	 */
	IEGLCompletionProposal[] getCorrections(IInvocationContext context, IProblemLocation[] locations) throws CoreException;

}
