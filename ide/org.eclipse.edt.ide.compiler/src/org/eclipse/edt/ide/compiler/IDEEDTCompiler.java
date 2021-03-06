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
package org.eclipse.edt.ide.compiler;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.edt.compiler.EDTCompiler;
import org.eclipse.edt.ide.core.IDEBaseCompiler;

public class IDEEDTCompiler extends IDEBaseCompiler {
	
	public IDEEDTCompiler() {
		super(new EDTCompiler());
	}
	
	@Override
	protected List<String> getSystemEnvironmentPathEntries() {
		List<String> list = new ArrayList(super.getSystemEnvironmentPathEntries());
		list.add(0, getPathToPluginDirectory("org.eclipse.edt.compiler", "lib"));
		return list;
	}
}
