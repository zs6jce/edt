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
package org.eclipse.edt.ide.ui.internal.contentassist.referencecompletion;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.edt.compiler.core.ast.CaseStatement;
import org.eclipse.edt.compiler.core.ast.Node;
import org.eclipse.edt.ide.core.internal.errors.ParseStack;
import org.eclipse.edt.ide.ui.internal.contentassist.proposalhandlers.EGLDeclarationProposalHandler;
import org.eclipse.edt.ide.ui.internal.contentassist.proposalhandlers.EGLFunctionMemberSearchProposalHandler;
import org.eclipse.jface.text.ITextViewer;

public class EGLCaseStatementReferenceCompletion extends EGLAbstractReferenceCompletion {

	/* (non-Javadoc)
	 * @see org.eclipse.edt.ide.ui.internal.contentassist.EGLAbstractReferenceCompletion#precompileContexts()
	 */
	protected void precompileContexts() {
		addContext("package a; handler a function a() case ("); //$NON-NLS-1$
		addContext("package a; handler a function a() case (a) when ("); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see org.eclipse.edt.ide.ui.internal.contentassist.EGLAbstractReferenceCompletion#returnCompletionProposals(com.ibm.etools.egl.pgm.errors.ParseStack, java.util.List, org.eclipse.jface.text.ITextViewer, int)
	 */
	protected List returnCompletionProposals(ParseStack parseStack, final String prefix, final ITextViewer viewer, final int documentOffset) {
		final List proposals = new ArrayList();
		getBoundASTNode(viewer, documentOffset, new String[] {"", "x", "x) end", "x)"}, new CompletedNodeVerifier() {
			public boolean nodeIsValid(Node astNode) {
				while(astNode != null) {
					if(astNode instanceof CaseStatement) {
						return true;
					}
					astNode = astNode.getParent();
				}
				return false;
			}
		}, new IBoundNodeProcessor() {
			public void processBoundNode(Node boundNode) {
				
				//Get all variable proposals
				proposals.addAll(
					new EGLDeclarationProposalHandler(viewer,
						documentOffset,
						prefix,
						boundNode)
						.getProposals(boundNode));
							
				//Get user function proposals with no return value
				proposals.addAll(
					new EGLFunctionMemberSearchProposalHandler(viewer, documentOffset, prefix, editor, true, boundNode).
						getProposals());
				
			}
		});
		
		return proposals;
	}

}
