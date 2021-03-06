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
package org.eclipse.edt.ide.core.internal.model;

import org.eclipse.edt.ide.core.internal.model.util.LRUCache;
import org.eclipse.edt.ide.core.model.IBuffer;


/**
 * An LRU cache of <code>IBuffers</code>.
 */
public class BufferCache extends OverflowingLRUCache {
/**
 * Constructs a new buffer cache of the given size.
 */
public BufferCache(int size) {
	super(size);
}
/**
 * Constructs a new buffer cache of the given size.
 */
public BufferCache(int size, int overflow) {
	super(size, overflow);
}
/**
 * Returns true if the buffer is successfully closed and
 * removed from the cache, otherwise false.
 *
 * <p>NOTE: this triggers an external removal of this buffer
 * by closing the buffer.
 */
protected boolean close(LRUCacheEntry entry) {
	IBuffer buffer= (IBuffer) entry._fValue;
	if (buffer.hasUnsavedChanges()) {
		return false;
	} else {
		buffer.close();
		return true;
	}
}
	/**
	 * Returns a new instance of the reciever.
	 */
	protected LRUCache newInstance(int size, int overflow) {
		return new BufferCache(size, overflow);
	}
}
