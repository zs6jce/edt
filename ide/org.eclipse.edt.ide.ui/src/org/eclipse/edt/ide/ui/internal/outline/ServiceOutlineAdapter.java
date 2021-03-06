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
package org.eclipse.edt.ide.ui.internal.outline;

import org.eclipse.edt.compiler.core.ast.Service;
import org.eclipse.edt.ide.ui.internal.PluginImages;
import org.eclipse.edt.ide.ui.internal.editor.EGLEditor;
import org.eclipse.jface.text.IRegion;

public class ServiceOutlineAdapter extends AbstractOutlineAdapter {

	public ServiceOutlineAdapter(EGLEditor editor) {
		super(editor);
		//TODO - need service icon here
		nodeIcon = PluginImages.DESC_OBJS_SERVICE;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.edt.ide.ui.internal.outline.IOutlineAdapter#getChildren(java.lang.Object)
	 */
	public Object[] getChildren(Object parentElement) {
		Service service = (Service) parentElement;
		return filterOutProperties(service.getContents()).toArray();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.edt.ide.ui.internal.outline.IOutlineAdapter#getText(java.lang.Object)
	 */
	public String getText(Object element) {
		return ((Service) element).getName().getCanonicalName();
	}

	public IRegion getHighlightRange(Object element) {
		Service serviceNode = (Service) element;
		return getPartNameHighlightRange(serviceNode);
	}

}
