/*******************************************************************************
 * Copyright © 2004, 2013 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *
 *******************************************************************************/
package org.eclipse.edt.ide.ui.internal.search;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class DelegatingLabelProvider extends LabelProvider
{
	private ILabelProvider fLabelProvider;
	private EGLSearchResultPage fPage;

	public DelegatingLabelProvider(EGLSearchResultPage page, ILabelProvider inner) {
		fPage= page;
		fLabelProvider= inner;
	}
	
	public ILabelProvider getLabelProvider() {
		return fLabelProvider;
	}

	public Image getImage(Object element) {
		return fLabelProvider.getImage(element);
	}

	public String getText(Object element) {
		int matchCount= fPage.getInput().getMatchCount(element);
		String text= fLabelProvider.getText(element);
		if (matchCount == 0)
			return text;
		if (matchCount == 1)
			return fLabelProvider.getText(element)+ " (" + 1 + " match)"; //$NON-NLS-1$ //$NON-NLS-2$
		return text + " (" + matchCount + " matches)"; //$NON-NLS-1$ //$NON-NLS-2$
	}

	public void dispose() {
		fLabelProvider.dispose();
		super.dispose();
	}
    
}
