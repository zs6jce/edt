/*******************************************************************************
 * Copyright © 2008, 2013 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *
 *******************************************************************************/
package org.eclipse.edt.ide.debug.javascript.internal.generators;

import java.util.HashMap;
import java.util.List;

import org.eclipse.edt.gen.AbstractGeneratorCommand;
import org.eclipse.edt.gen.deployment.javascript.ContextAwareHTMLGenerator;

public class DebugHTMLGenerator extends ContextAwareHTMLGenerator
{
	public DebugHTMLGenerator( AbstractGeneratorCommand processor, List egldds, HashMap eglParameters, String userMsgLocale, String runtimeMsgLocale )
	{
		super( processor, egldds, eglParameters, userMsgLocale, runtimeMsgLocale );
	}
	
	@Override
	protected boolean isDebug()
	{
		return true;
	}
}
