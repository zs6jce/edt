package org.eclipse.edt.tests.validation.junit.annotations;

import java.util.List;

import org.eclipse.edt.tests.validation.junit.ValidationTestCase;

/*
 * A JUnit test case for the file EGLSource/annotations/property.egl
 */
public class PropertyTest extends ValidationTestCase {

	public PropertyTest() {
		super( "EGLSource/annotations/property.egl", false );
	}

	/*
	 * field1 int {@property{}};
	 * 0 validation messages are expected.
	 */
	public void testLine2() {
		List messages = getMessagesAtLine( 2 );
		assertEquals( 0, messages.size() );
	}

	/*
	 * field2 int {@property{getmethod = "getit"}};
	 * 0 validation messages are expected.
	 */
	public void testLine3() {
		List messages = getMessagesAtLine( 3 );
		assertEquals( 0, messages.size() );
	}

	/*
	 * field3 int {@property{setmethod = "setit"}};
	 * 0 validation messages are expected.
	 */
	public void testLine4() {
		List messages = getMessagesAtLine( 4 );
		assertEquals( 0, messages.size() );
	}

	/*
	 * field4 int {@property{getmethod = "getit", setmethod = "setit"}};
	 * 0 validation messages are expected.
	 */
	public void testLine5() {
		List messages = getMessagesAtLine( 5 );
		assertEquals( 0, messages.size() );
	}

	/*
	 * field5 int {@property{getmethod = "setit", setmethod = "getit"}};
	 * 0 validation messages are expected.
	 */
	public void testLine6() {
		List messages = getMessagesAtLine( 6 );
		assertEquals( 0, messages.size() );
	}

	/*
	 * field2 = 3;
	 * 1 validation message is expected.
	 */
	public void testLine14() {
		List messages = getMessagesAtLine( 14 );
		assertEquals( 1, messages.size() );
	}

	/*
	 * temp = field3;
	 * 1 validation message is expected.
	 */
	public void testLine15() {
		List messages = getMessagesAtLine( 15 );
		assertEquals( 1, messages.size() );
	}

	/*
	 * field4 = 3;
	 * 0 validation messages are expected.
	 */
	public void testLine17() {
		List messages = getMessagesAtLine( 17 );
		assertEquals( 0, messages.size() );
	}

	/*
	 * temp = field4;
	 * 0 validation messages are expected.
	 */
	public void testLine18() {
		List messages = getMessagesAtLine( 18 );
		assertEquals( 0, messages.size() );
	}

	/*
	 * hand1.field2 = 3;
	 * 1 validation message is expected.
	 */
	public void testLine27() {
		List messages = getMessagesAtLine( 27 );
		assertEquals( 1, messages.size() );
	}

	/*
	 * temp = hand1.field3;
	 * 1 validation message is expected.
	 */
	public void testLine28() {
		List messages = getMessagesAtLine( 28 );
		assertEquals( 1, messages.size() );
	}

	/*
	 * hand1.field4 = 3;
	 * 0 validation messages are expected.
	 */
	public void testLine30() {
		List messages = getMessagesAtLine( 30 );
		assertEquals( 0, messages.size() );
	}

	/*
	 * temp = hand1.field4;
	 * 0 validation messages are expected.
	 */
	public void testLine31() {
		List messages = getMessagesAtLine( 31 );
		assertEquals( 0, messages.size() );
	}
}