/*******************************************************************************
 * Copyright © 2000, 2013 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *
 *******************************************************************************/
package org.eclipse.edt.ide.core.internal.model.index.impl;

import org.eclipse.edt.compiler.internal.core.utils.CharOperation;

public final class WordEntryHashedArray {

// to avoid using Enumerations, walk the objects skipping nulls
public WordEntry elements[];
public int elementSize; // number of elements in the table
public int threshold;

public WordEntryHashedArray(int size) {
	if (size < 7) size = 7;
	this.elements = new WordEntry[2 * size + 1];
	this.elementSize = 0;
	this.threshold = size + 1; // size is the expected number of elements
}

public WordEntry add(WordEntry entry) {
	int length = elements.length;
	char[] word = entry.getWord();
	int index = CharOperation.hashCode(word) % length;
	WordEntry current;
	while ((current = elements[index]) != null) {
		if (CharOperation.equals(current.getWord(), word)) return elements[index] = entry;
		if (++index == length) index = 0;
	}
	elements[index] = entry;

	// assumes the threshold is never equal to the size of the table
	if (++elementSize > threshold) grow();
	return entry;
}

public WordEntry[] asArray() {
	WordEntry[] array = new WordEntry[elementSize];
	int count = 0;
	for (int i = 0, length = elements.length; i < length; i++) {
		WordEntry current = elements[i];
		if (current != null)
			array[count++] = current;
	}
	if (count < array.length) // add a little protection because of 40950
		System.arraycopy(array, 0, array = new WordEntry[count], 0, count);
	return array;
}

public WordEntry get(char[] word) {
	int length = elements.length;
	int index = CharOperation.hashCode(word) % length;
	WordEntry current;
	while ((current = elements[index]) != null) {
		if (CharOperation.equals(current.getWord(), word)) return current;
		if (++index == length) index = 0;
	}
	return null;
}

private void grow() {
	WordEntryHashedArray newArray = new WordEntryHashedArray(elementSize * 2); // double the number of expected elements
	for (int i = 0, length = elements.length; i < length; i++)
		if (elements[i] != null)
			newArray.add(elements[i]);

	this.elements = newArray.elements;
	this.elementSize = newArray.elementSize;
	this.threshold = newArray.threshold;
}

public String toString() {
	String s = ""; //$NON-NLS-1$
	WordEntry[] entries = asArray();
	for (int i = 0, length = entries.length; i < length; i++)
		s += entries[i].toString() + "\n"; 	//$NON-NLS-1$
	return s;
}
}
