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
package org.eclipse.edt.ide.rui.server;


public class AbstractPreviewContext extends AbstractContext {

	private IServerListener serverListener;
	
	public AbstractPreviewContext(String url, Integer key, IServerContentProvider provider, IServerListener listener){
		super(url, key, provider);
		this.serverListener = listener;
	}
	
	public IServerListener getServerListener(){
		return serverListener;
	}
	
}
