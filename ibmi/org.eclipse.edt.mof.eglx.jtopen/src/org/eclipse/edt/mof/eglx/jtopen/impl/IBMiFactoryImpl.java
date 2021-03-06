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
package org.eclipse.edt.mof.eglx.jtopen.impl;

import org.eclipse.edt.mof.EClass;
import org.eclipse.edt.mof.eglx.jtopen.IBMiCallStatement;
import org.eclipse.edt.mof.eglx.jtopen.IBMiFactory;
import org.eclipse.edt.mof.eglx.jtopen.IBMiFunction;
import org.eclipse.edt.mof.impl.EFactoryImpl;


public class IBMiFactoryImpl extends EFactoryImpl implements IBMiFactory {

	@Override
	public EClass getIBMiFunctionEClass() {
		return (EClass)getTypeNamed(IBMiFunction);
	}

	@Override
	public IBMiFunction createIBMiFunction() {
		return (IBMiFunction)getIBMiFunctionEClass().newInstance();
	}

	@Override
	public EClass getIBMiCallStatementEClass() {
		return (EClass)getTypeNamed(IBMiCallStatement);
	}

	@Override
	public IBMiCallStatement createIBMiCallStatement() {
		return (IBMiCallStatement)getIBMiCallStatementEClass().newInstance();
	}

}
