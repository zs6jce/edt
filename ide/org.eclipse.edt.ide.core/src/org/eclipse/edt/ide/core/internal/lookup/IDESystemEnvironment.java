/*******************************************************************************
 * Copyright © 2012 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *
 *******************************************************************************/
package org.eclipse.edt.ide.core.internal.lookup;

import java.util.List;

import org.eclipse.edt.compiler.ICompiler;
import org.eclipse.edt.compiler.ISystemEnvironment;
import org.eclipse.edt.compiler.ISystemPackageBuildPathEntry;
import org.eclipse.edt.compiler.SystemEnvironment;
import org.eclipse.edt.mof.serialization.IEnvironment;

public class IDESystemEnvironment extends SystemEnvironment {

	public IDESystemEnvironment(IEnvironment irEnv,
			ISystemEnvironment parentEnv,
			List<String> implicitlyUsedEnumerations, ICompiler compiler) {
		super(irEnv, parentEnv, implicitlyUsedEnumerations, compiler);
	}
	
	@Override
	protected void addSystemPackages(List<ISystemPackageBuildPathEntry> entries) {
		// TODO Auto-generated method stub
		super.addSystemPackages(entries);
		for (ISystemPackageBuildPathEntry entry : entries) {
			ZipFileBuildPathEntryManager.addSystemPathEntry(getCompiler(), entry);
		}
	}

}