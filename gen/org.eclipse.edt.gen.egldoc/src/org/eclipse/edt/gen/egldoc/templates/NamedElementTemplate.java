/*******************************************************************************
 * Copyright © 2013 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *
 *******************************************************************************/
package org.eclipse.edt.gen.egldoc.templates;

import org.eclipse.edt.gen.egldoc.Context;
import org.eclipse.edt.mof.codegen.api.TabbedWriter;
import org.eclipse.edt.mof.egl.NamedElement;

public class NamedElementTemplate extends EGLDocTemplate {
	
	public void genDeclaration(NamedElement field, Context ctx, TabbedWriter out) {
		out.println(field.getCaseSensitiveName());
	}
	public void genDeclaration(String field, Context ctx, TabbedWriter out) {
		out.println(field);
	}
	
}
