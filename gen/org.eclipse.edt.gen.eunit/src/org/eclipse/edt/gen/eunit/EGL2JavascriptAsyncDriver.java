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
package org.eclipse.edt.gen.eunit;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.edt.compiler.ICompiler;
import org.eclipse.edt.compiler.internal.interfaces.IGenerationMessageRequestor;
import org.eclipse.edt.gen.AbstractGeneratorCommand;

public class EGL2JavascriptAsyncDriver extends EGL2JavascriptDriver {

	public EGL2JavascriptAsyncDriver(){
		super();
	}
	
	public static void main(String[] args){
		start(args, null, new NullEUnitGenerationNotifier());
	}
	
	public static void start(String[] args, ICompiler compiler, IEUnitGenerationNotifier eckGenerationNotifier) {
		List<String> arguments = new ArrayList<String>();
		for (int i = 0; i < args.length; i++) {
			arguments.add(args[i]);
		}
		arguments.add("-c");
		arguments.add("org.eclipse.edt.gen.eunit.EUnitJavascriptAsyncDriverGenerationContributor");
		arguments.add("org.eclipse.edt.gen.eunit.EUnitJavascriptDriverGenerationContributor");
		arguments.add("org.eclipse.edt.gen.eunit.EUnitDriverGenerationContributor");
		EGL2JavascriptAsyncDriver genPart = new EGL2JavascriptAsyncDriver();
		genPart.startGeneration(arguments.toArray(new String[arguments.size()]), compiler, eckGenerationNotifier);
	}
	
	@Override
	protected EUnitRunAllDriverGenerator getEckRunAllDriverGenerator(
			AbstractGeneratorCommand processor,
			IGenerationMessageRequestor req,
			IEUnitGenerationNotifier eckGenerationNotifier) {		
		return new EUnitRunAllJavascriptAsyncDriverGenerator(processor, req, javascriptDriverPartNameAppend, eckGenerationNotifier);
	}

}
