/*******************************************************************************
 * Copyright © 2013 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *
 *******************************************************************************/
package org.eclipse.edt.tests.validation.junit.part;

import java.util.List;

import org.eclipse.edt.tests.validation.junit.ValidationTestCase;

/*
 * A JUnit test case for the file EGLSource/part/function.egl
 */
public class FunctionTest extends ValidationTestCase {

	public FunctionTest() {
		super( "EGLSource/part/function.egl", false );
	}

	/*
	 * Library validateParamsAndDeclarationsLibrary
	 * 0 validation messages are expected.
	 */
	public void testLine12() {
		List messages = getMessagesAtLine( 12 );
		assertEquals( 0, messages.size() );
	}

	/*
	 * p1 notEmptyRec,
	 * 0 validation messages are expected.
	 */
	public void testLine14() {
		List messages = getMessagesAtLine( 14 );
		assertEquals( 0, messages.size() );
	}

	/*
	 * p3 emptyFlexRec,
	 * 1 validation message is expected.
	 */
	public void testLine16() {
		List messages = getMessagesAtLine( 16 );
		assertEquals( 1, messages.size() );
	}

	/*
	 * p4 emptyFixedRec,
	 * 1 validation message is expected.
	 */
	public void testLine17() {
		List messages = getMessagesAtLine( 17 );
		assertEquals( 1, messages.size() );
	}

	/*
	 * p5 cantBeResolved
	 * 1 validation message is expected.
	 */
	public void testLine18() {
		List messages = getMessagesAtLine( 18 );
		assertEquals( 1, messages.size() );
	}

	/*
	 * function main() end
	 * 0 validation messages are expected.
	 */
	public void testLine32() {
		List messages = getMessagesAtLine( 32 );
		assertEquals( 0, messages.size() );
	}

	/*
	 * p1 int,
	 * 0 validation messages are expected.
	 */
	public void testLine37() {
		List messages = getMessagesAtLine( 37 );
		assertEquals( 0, messages.size() );
	}

	/*
	 * p1 int,
	 * 1 validation message is expected.
	 * It is expected to contain "The same name p1 also appears as variable, parameter, use or constant declaration in part validateParamsAndDeclarations.".
	 */
	public void testLine38() {
		List messages = getMessagesAtLine( 38 );
		assertEquals( 1, messages.size() );
		
		Object messageWithSubstring = messageWithSubstring( messages, "The same name p1 also appears as variable, parameter, use or constant declaration in part validateParamsAndDeclarations." );
		if( messageWithSubstring == null ) fail( "No message with substring \"The same name p1 also appears as variable, parameter, use or constant declaration in part validateParamsAndDeclarations.\" was issued." );
	}

	/*
	 * recParm1 emptyFlexRec,
	 * 1 validation message is expected.
	 * It is expected to contain "Invalid parameter recParm1. There must be at least one structure item in the contents of the record emptyFlexRec.".
	 */
	public void testLine39() {
		List messages = getMessagesAtLine( 39 );
		assertEquals( 1, messages.size() );
		
		Object messageWithSubstring = messageWithSubstring( messages, "Invalid parameter recParm1. There must be at least one structure item in the contents of the record emptyFlexRec." );
		if( messageWithSubstring == null ) fail( "No message with substring \"Invalid parameter recParm1. There must be at least one structure item in the contents of the record emptyFlexRec.\" was issued." );
	}

	/*
	 * p1 validateParamsAndDeclarationsLibrary,
	 * 1 validation message is expected.
	 * It is expected to contain "The type validateParamsAndDeclarationsLibrary is not a valid type for a data declaration.".
	 */
	public void testLine48() {
		List messages = getMessagesAtLine( 48 );
		assertEquals( 1, messages.size() );
		
		Object messageWithSubstring = messageWithSubstring( messages, "The type validateParamsAndDeclarationsLibrary is not a valid type for a data declaration." );
		if( messageWithSubstring == null ) fail( "No message with substring \"The type validateParamsAndDeclarationsLibrary is not a valid type for a data declaration.\" was issued." );
	}

	/*
	 * p2 validatePgm,
	 * 1 validation message is expected.
	 * It is expected to contain "The type validatePgm is not a valid type for a data declaration.".
	 */
	public void testLine49() {
		List messages = getMessagesAtLine( 49 );
		assertEquals( 1, messages.size() );
		
		Object messageWithSubstring = messageWithSubstring( messages, "The type validatePgm is not a valid type for a data declaration." );
		if( messageWithSubstring == null ) fail( "No message with substring \"The type validatePgm is not a valid type for a data declaration.\" was issued." );
	}

	/*
	 * p3 validateParms,
	 * 1 validation message is expected.
	 * It is expected to contain "The type validateParms cannot be resolved.".
	 */
	public void testLine50() {
		List messages = getMessagesAtLine( 50 );
		assertEquals( 1, messages.size() );
		
		Object messageWithSubstring = messageWithSubstring( messages, "The type validateParms cannot be resolved." );
		if( messageWithSubstring == null ) fail( "No message with substring \"The type validateParms cannot be resolved.\" was issued." );
	}

	/*
	 * p4a validateParms[],
	 * 1 validation message is expected.
	 * It is expected to contain "The type validateParms cannot be resolved.".
	 */
	public void testLine51() {
		List messages = getMessagesAtLine( 51 );
		assertEquals( 1, messages.size() );
		
		Object messageWithSubstring = messageWithSubstring( messages, "The type validateParms cannot be resolved." );
		if( messageWithSubstring == null ) fail( "No message with substring \"The type validateParms cannot be resolved.\" was issued." );
	}

	/*
	 * p5 annot1,
	 * 1 validation message is expected.
	 * It is expected to contain "The type annot1 is not a valid type for a data declaration.".
	 */
	public void testLine52() {
		List messages = getMessagesAtLine( 52 );
		assertEquals( 1, messages.size() );
		
		Object messageWithSubstring = messageWithSubstring( messages, "The type annot1 is not a valid type for a data declaration." );
		if( messageWithSubstring == null ) fail( "No message with substring \"The type annot1 is not a valid type for a data declaration.\" was issued." );
	}

	/*
	 * p15 sqlRecord[],
	 * 0 validation messages are expected.
	 */
	public void testLine54() {
		List messages = getMessagesAtLine( 54 );
		assertEquals( 0, messages.size() );
	}

	/*
	 * p16 myService in,
	 * 1 validation message is expected.
	 * It is expected to contain "The type myService is not a valid type for a data declaration.".
	 */
	public void testLine55() {
		List messages = getMessagesAtLine( 55 );
		assertEquals( 1, messages.size() );
		
		Object messageWithSubstring = messageWithSubstring( messages, "The type myService is not a valid type for a data declaration." );
		if( messageWithSubstring == null ) fail( "No message with substring \"The type myService is not a valid type for a data declaration.\" was issued." );
	}

	/*
	 * p17 myService out,
	 * 1 validation message is expected.
	 * It is expected to contain "The type myService is not a valid type for a data declaration.".
	 */
	public void testLine56() {
		List messages = getMessagesAtLine( 56 );
		assertEquals( 1, messages.size() );
		
		Object messageWithSubstring = messageWithSubstring( messages, "The type myService is not a valid type for a data declaration." );
		if( messageWithSubstring == null ) fail( "No message with substring \"The type myService is not a valid type for a data declaration.\" was issued." );
	}

	/*
	 * p18 myService inout,
	 * 1 validation message is expected.
	 * It is expected to contain "The type myService is not a valid type for a data declaration.".
	 */
	public void testLine57() {
		List messages = getMessagesAtLine( 57 );
		assertEquals( 1, messages.size() );
		
		Object messageWithSubstring = messageWithSubstring( messages, "The type myService is not a valid type for a data declaration." );
		if( messageWithSubstring == null ) fail( "No message with substring \"The type myService is not a valid type for a data declaration.\" was issued." );
	}

	/*
	 * p22 int in,
	 * 0 validation messages are expected.
	 */
	public void testLine58() {
		List messages = getMessagesAtLine( 58 );
		assertEquals( 0, messages.size() );
	}

	/*
	 * p23 int out,
	 * 0 validation messages are expected.
	 */
	public void testLine59() {
		List messages = getMessagesAtLine( 59 );
		assertEquals( 0, messages.size() );
	}

	/*
	 * p24 int inout,
	 * 0 validation messages are expected.
	 */
	public void testLine60() {
		List messages = getMessagesAtLine( 60 );
		assertEquals( 0, messages.size() );
	}

	/*
	 * p25 myService[],
	 * 1 validation message is expected.
	 * It is expected to contain "The type myService is not a valid type for a data declaration.".
	 */
	public void testLine61() {
		List messages = getMessagesAtLine( 61 );
		assertEquals( 1, messages.size() );
		
		Object messageWithSubstring = messageWithSubstring( messages, "The type myService is not a valid type for a data declaration." );
		if( messageWithSubstring == null ) fail( "No message with substring \"The type myService is not a valid type for a data declaration.\" was issued." );
	}

	/*
	 * p26 myInterface[],
	 * 0 validation messages are expected.
	 */
	public void testLine62() {
		List messages = getMessagesAtLine( 62 );
		assertEquals( 0, messages.size() );
	}

	/*
	 * p28 undeclaredVar,
	 * 1 validation message is expected.
	 * It is expected to contain "The type undeclaredVar cannot be resolved".
	 */
	public void testLine63() {
		List messages = getMessagesAtLine( 63 );
		assertEquals( 1, messages.size() );
		
		Object messageWithSubstring = messageWithSubstring( messages, "The type undeclaredVar cannot be resolved" );
		if( messageWithSubstring == null ) fail( "No message with substring \"The type undeclaredVar cannot be resolved\" was issued." );
	}

	/*
	 * p29 undeclaredVar[],
	 * 1 validation message is expected.
	 * It is expected to contain "The type undeclaredVar cannot be resolved".
	 */
	public void testLine64() {
		List messages = getMessagesAtLine( 64 );
		assertEquals( 1, messages.size() );
		
		Object messageWithSubstring = messageWithSubstring( messages, "The type undeclaredVar cannot be resolved" );
		if( messageWithSubstring == null ) fail( "No message with substring \"The type undeclaredVar cannot be resolved\" was issued." );
	}

	/*
	 * p31 boolean,
	 * 0 validation messages are expected.
	 */
	public void testLine69() {
		List messages = getMessagesAtLine( 69 );
		assertEquals( 0, messages.size() );
	}

	/*
	 * function func(p1 boolean);
	 * 0 validation messages are expected.
	 */
	public void testLine78() {
		List messages = getMessagesAtLine( 78 );
		assertEquals( 0, messages.size() );
	}

	/*
	 * function func2() returns(boolean);
	 * 0 validation messages are expected.
	 */
	public void testLine79() {
		List messages = getMessagesAtLine( 79 );
		assertEquals( 0, messages.size() );
	}

	/*
	 * function func(p1 boolean in);
	 * 0 validation messages are expected.
	 */
	public void testLine83() {
		List messages = getMessagesAtLine( 83 );
		assertEquals( 0, messages.size() );
	}

	/*
	 * function func2() returns(boolean);
	 * 0 validation messages are expected.
	 */
	public void testLine84() {
		List messages = getMessagesAtLine( 84 );
		assertEquals( 0, messages.size() );
	}

	/*
	 * function func(p1 boolean in);
	 * 0 validation messages are expected.
	 */
	public void testLine88() {
		List messages = getMessagesAtLine( 88 );
		assertEquals( 0, messages.size() );
	}

	/*
	 * function func2() returns(boolean);
	 * 0 validation messages are expected.
	 */
	public void testLine89() {
		List messages = getMessagesAtLine( 89 );
		assertEquals( 0, messages.size() );
	}

	/*
	 * function main() returns (int) end
	 * 0 validation messages are expected.
	 */
	public void testLine101() {
		List messages = getMessagesAtLine( 101 );
		assertEquals( 0, messages.size() );
	}

	/*
	 * function func() returns (boolean) end
	 * 0 validation messages are expected.
	 */
	public void testLine102() {
		List messages = getMessagesAtLine( 102 );
		assertEquals( 0, messages.size() );
	}

	/*
	 * function func2() returns (int[]) end
	 * 0 validation messages are expected.
	 */
	public void testLine103() {
		List messages = getMessagesAtLine( 103 );
		assertEquals( 0, messages.size() );
	}

	/*
	 * function func4() returns (dictionary) end
	 * 0 validation messages are expected.
	 */
	public void testLine104() {
		List messages = getMessagesAtLine( 104 );
		assertEquals( 0, messages.size() );
	}

	/*
	 * function func7() returns (myService) end
	 * 1 validation message is expected.
	 * It is expected to contain "The type myService is not a valid type for a data declaration.".
	 */
	public void testLine105() {
		List messages = getMessagesAtLine( 105 );
		assertEquals( 1, messages.size() );
		
		Object messageWithSubstring = messageWithSubstring( messages, "The type myService is not a valid type for a data declaration." );
		if( messageWithSubstring == null ) fail( "No message with substring \"The type myService is not a valid type for a data declaration.\" was issued." );
	}

	/*
	 * function func8() returns (myInterface) end
	 * 0 validation messages are expected.
	 */
	public void testLine106() {
		List messages = getMessagesAtLine( 106 );
		assertEquals( 0, messages.size() );
	}

	/*
	 * function func9() returns (notEmptyRec) end
	 * 0 validation messages are expected.
	 */
	public void testLine107() {
		List messages = getMessagesAtLine( 107 );
		assertEquals( 0, messages.size() );
	}

	/*
	 * function func10() returns (validateReturnProgram) end
	 * 1 validation message is expected.
	 * It is expected to contain "The type validateReturnProgram is not a valid type for a data declaration.".
	 */
	public void testLine109() {
		List messages = getMessagesAtLine( 109 );
		assertEquals( 1, messages.size() );
		
		Object messageWithSubstring = messageWithSubstring( messages, "The type validateReturnProgram is not a valid type for a data declaration." );
		if( messageWithSubstring == null ) fail( "No message with substring \"The type validateReturnProgram is not a valid type for a data declaration.\" was issued." );
	}

	/*
	 * function func11() returns (annot1) end
	 * 1 validation message is expected.
	 * It is expected to contain "The type annot1 is not a valid type for a data declaration.".
	 */
	public void testLine110() {
		List messages = getMessagesAtLine( 110 );
		assertEquals( 1, messages.size() );
		
		Object messageWithSubstring = messageWithSubstring( messages, "The type annot1 is not a valid type for a data declaration." );
		if( messageWithSubstring == null ) fail( "No message with substring \"The type annot1 is not a valid type for a data declaration.\" was issued." );
	}

	/*
	 * function func12() returns (undeclaredItem) end
	 * 1 validation message is expected.
	 * It is expected to contain "The type undeclaredItem cannot be resolved".
	 */
	public void testLine112() {
		List messages = getMessagesAtLine( 112 );
		assertEquals( 1, messages.size() );
		
		Object messageWithSubstring = messageWithSubstring( messages, "The type undeclaredItem cannot be resolved" );
		if( messageWithSubstring == null ) fail( "No message with substring \"The type undeclaredItem cannot be resolved\" was issued." );
	}

	/*
	 * super();
	 * 0 validation messages are expected.
	 */
	public void testLine120() {
		List messages = getMessagesAtLine( 120 );
		assertEquals( 0, messages.size() );
	}

	/*
	 * super();
	 * 1 validation message is expected.
	 */
	public void testLine121() {
		List messages = getMessagesAtLine( 121 );
		assertEquals( 1, messages.size() );
	}

	/*
	 * super("xyz");
	 * 2 validation messages are expected.
	 */
	public void testLine122() {
		List messages = getMessagesAtLine( 122 );
		assertEquals( 2, messages.size() );
	}

	/*
	 * this();
	 * 0 validation messages are expected.
	 */
	public void testLine125() {
		List messages = getMessagesAtLine( 125 );
		assertEquals( 0, messages.size() );
	}

	/*
	 * this();
	 * 1 validation message is expected.
	 */
	public void testLine126() {
		List messages = getMessagesAtLine( 126 );
		assertEquals( 1, messages.size() );
	}

	/*
	 * this(s, i, 456, 789, 123, "aaaa");
	 * 1 validation message is expected.
	 */
	public void testLine129() {
		List messages = getMessagesAtLine( 129 );
		assertEquals( 1, messages.size() );
	}

	/*
	 * this(i, s);
	 * 0 validation messages are expected.
	 */
	public void testLine132() {
		List messages = getMessagesAtLine( 132 );
		assertEquals( 0, messages.size() );
	}

	/*
	 * super(i, s, b, d);
	 * 1 validation message is expected.
	 */
	public void testLine135() {
		List messages = getMessagesAtLine( 135 );
		assertEquals( 1, messages.size() );
	}

	/*
	 * super();
	 * 1 validation message is expected.
	 */
	public void testLine138() {
		List messages = getMessagesAtLine( 138 );
		assertEquals( 1, messages.size() );
	}

	/*
	 * this();
	 * 1 validation message is expected.
	 */
	public void testLine139() {
		List messages = getMessagesAtLine( 139 );
		assertEquals( 1, messages.size() );
	}
}
