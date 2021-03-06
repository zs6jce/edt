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
package org.eclipse.edt.ide.jtopen;

import org.eclipse.osgi.util.NLS;



public class Messages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.edt.ide.jtopen.Messages"; //$NON-NLS-1$

	private Messages() {
		// Do not instantiate
	}

	static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
	
	public static String TitleAddIBMiBinding;
	public static String DescAddIBMiBinding;
	public static String LabelIBMiBindingName;
	public static String LabelSystem;
	public static String LabelUserId;
	public static String IBMiBindingBlankError;
	public static String IBMiBindingSystemBlankError;
	public static String IBMiBindingDateFormatError;
	public static String IBMiBindingTimeFormatError;
	public static String LabelPassword;
	public static String LabelLibrary;
	public static String LabelTextEncoding;
	public static String LabelTimezone;
	public static String LabelDateFormat;
	public static String LabelDateSeparator;
	public static String LabelTimeFormat;
	public static String LabelTimeSeparator;
	
	public static String IBMiBindingDetailSecTitle;
	public static String IBMiBindingDetailSecDescp;

}
