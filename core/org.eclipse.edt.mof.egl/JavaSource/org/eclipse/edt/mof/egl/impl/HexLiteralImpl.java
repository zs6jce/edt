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
package org.eclipse.edt.mof.egl.impl;

import org.eclipse.edt.mof.egl.HexLiteral;
import org.eclipse.edt.mof.egl.Type;
import org.eclipse.edt.mof.egl.utils.IRUtils;


public class HexLiteralImpl extends PrimitiveTypeLiteralImpl implements HexLiteral {

	@Override
	public Type getType() {
		return IRUtils.getEGLPrimitiveType(Type_Hex, getValue().length());
	}

	@Override
	public Object getObjectValue() {
		return getValue();
	}
}
