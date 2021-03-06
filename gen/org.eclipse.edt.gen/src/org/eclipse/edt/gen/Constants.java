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
package org.eclipse.edt.gen;

public class Constants {
	private Constants() {}

	// command parameter internal names
	public static final String parameter_output = "output";
	public static final String parameter_part = "part";
	public static final String parameter_root = "root";
	public static final String parameter_contribution = "contribution";
	public static final String parameter_report = "report";
	public static final String parameter_report_dir = "reportDir";
	public static final String parameter_report_dir_default = "genReports/";
	public static final String parameter_headerFile = "headerFile";

	// Extension to use for generation reports
	public static final String report_fileExtension = ".html";

	// temporary variable prefix
	public static final String temporaryVariablePrefix = "eze$Temp";
	public static final String temporaryVariableLogicallyNotNullablePrefix = "eze$LNNTemp";

	// these are sub key values used as context hashmap keys
	public static final String SubKey_functionArgumentTemporaryVariable = "functionArgumentTemporaryVariable";
	public static final String SubKey_functionHasReturnStatement = "functionHasReturnStatement";
	public static final String SubKey_functionArgumentNeedsWrapping = "functionArgumentNeedsWrapping";
	public static final String SubKey_GeneratorAnnotations = "SubKey_GeneratorAnnotations";
	public static final String SubKey_statementNeedsLabel = "statementNeedsLabel";
	public static final String SubKey_statementHasBeenReorganized = "statementHasBeenReorganized";

	// invoke names
	public static final String isAssignmentBreakupWanted = "isAssignmentBreakupWanted";
	public static final String isAssignmentArrayMatchingWanted = "isAssignmentArrayMatchingWanted";
	public static final String isListReorganizationWanted = "isListReorganizationWanted";
	public static final String isMathLibDecimalBoxingWanted = "isMathLibDecimalBoxingWanted";
	public static final String isStringLibFormatBoxingWanted = "isStringLibFormatBoxingWanted";
	public static final String isStatementRequiringWrappedParameters = "isStatementRequiringWrappedParameters";
	
	public static final String EGLMESSAGE_MISSING_HEADER_FILE = "7000";
	public static final String EGLMESSAGE_SUBTYPE_NOT_SUPPORTED = "9950";
	public static final String EGLMESSAGE_PARTTYPE_NOT_SUPPORTED = "9951";

	// these are sub key values used on annotations 
	public static final String SubKey_uri = "uri";
	
	// part names
	public static final String signature_Resource = "eglx.lang.Resource";
	public static final String signature_ExternalName = "eglx.lang.ExternalName";
}
