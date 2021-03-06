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
package org.eclipse.edt.gen;

public interface GenerationRegistry {
	public void registerCommandOptions(CommandOption[] options);
	public void registerTemplatePath(String[] path);
	public void registerNativeTypePath(String[] path);
	public void registerPrimitiveTypePath(String[] path);
	public void registerMessagePath(String[] path);
	public void registerSupportedPartTypes(String[] partTypes);
	public void registerSupportedStereotypes(String[] stereotypes);
}
