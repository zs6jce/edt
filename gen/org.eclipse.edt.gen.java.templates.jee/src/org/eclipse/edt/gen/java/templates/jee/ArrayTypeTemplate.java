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
package org.eclipse.edt.gen.java.templates.jee;

import org.eclipse.edt.gen.java.Context;
import org.eclipse.edt.gen.java.jee.Constants;
import org.eclipse.edt.mof.codegen.api.TabbedWriter;
import org.eclipse.edt.mof.egl.ArrayType;
import org.eclipse.edt.mof.egl.Field;

public class ArrayTypeTemplate extends org.eclipse.edt.gen.java.templates.ArrayTypeTemplate implements Constants {

	public void genJsonTypeDependentOptions(ArrayType type, Context ctx, TabbedWriter out) {
		ctx.invoke(genJsonTypeDependentOptions, type.getElementType(), ctx, out);
	}
	public void preGenAddXMLSchemaType(ArrayType type, Context ctx, Field field){
		// if there is not a scema type we may need to add it for type like time, date, timestamp
		ctx.invoke(preGenAddXMLSchemaType, type.getElementType(), ctx, field);
	}
}
