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
package org.eclipse.edt.compiler.internal.core.builder;

import java.util.Arrays;




public class StructureChangeEvaluator {

	public static boolean isStructuredChange (byte[] byteArray1, byte[] byteArray2 ){
		return !Arrays.equals(byteArray1, byteArray2);		
	}

}
