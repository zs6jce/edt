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
package org.eclipse.edt.ide.ui.internal.wizards;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IExecutableExtensionFactory;

public class RestBindingWizardPageFactory implements IExecutableExtensionFactory, IExecutableExtension {

	private String pageName;
	
	@Override
	public Object create() throws CoreException {
		return new RestBindingWizardPage( pageName );
	}

	@Override
	public void setInitializationData(IConfigurationElement contribution, String arg1, Object arg2) throws CoreException {
		pageName = contribution.getAttribute("name");
		
	}
}
