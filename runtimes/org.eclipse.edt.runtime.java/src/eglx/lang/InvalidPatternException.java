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
package eglx.lang;
import org.eclipse.edt.javart.resources.*;
import org.eclipse.edt.javart.*;
import eglx.lang.AnyException;
import org.eclipse.edt.runtime.java.eglx.lang.EString;
import java.lang.String;
@SuppressWarnings("unused")
@javax.xml.bind.annotation.XmlRootElement(name="InvalidPatternException")
public class InvalidPatternException extends eglx.lang.AnyException {
	private static final long serialVersionUID = 10L;
	@javax.xml.bind.annotation.XmlTransient
	public String pattern;
	public InvalidPatternException() {
		super();
	}
	{
		ezeInitialize();
	}
	public void ezeCopy(Object source) {
		ezeCopy((InvalidPatternException) source);
	}
	public void ezeCopy(eglx.lang.AnyValue source) {
		this.pattern = ((InvalidPatternException) source).pattern;
	}
	public InvalidPatternException ezeNewValue(Object... args) {
		return new InvalidPatternException();
	}
	public void ezeSetEmpty() {
		pattern = "";
	}
	public boolean isVariableDataLength() {
		return false;
	}
	public void loadFromBuffer(ByteStorage buffer, Program program) {
	}
	public int sizeInBytes() {
		return 0;
	}
	public void storeInBuffer(ByteStorage buffer) {
	}
	public void ezeInitialize() {
		pattern = "";
	}
	@org.eclipse.edt.javart.json.Json(name="pattern", clazz=EString.class, asOptions={})
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String ezeValue) {
		pattern = ezeValue;
	}
}
