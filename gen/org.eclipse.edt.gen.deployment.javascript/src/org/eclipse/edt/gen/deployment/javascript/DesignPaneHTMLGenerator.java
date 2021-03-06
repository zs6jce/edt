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
package org.eclipse.edt.gen.deployment.javascript;

import java.util.HashMap;
import java.util.List;

import org.eclipse.edt.gen.AbstractGeneratorCommand;

public class DesignPaneHTMLGenerator extends VeHTMLGenerator {

	public DesignPaneHTMLGenerator(AbstractGeneratorCommand processor, List egldds,
			HashMap eglParameters, String userMsgLocale, String runtimeMsgLocale) {
		super(processor, egldds, eglParameters, userMsgLocale, runtimeMsgLocale);
	}

	protected boolean getEnableEditing() {
		return true;
	}

}
