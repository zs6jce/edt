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

package org.eclipse.edt.ide.core.model;

import org.eclipse.core.runtime.IProgressMonitor;

/**
 * Common protocol for EGL elements that must be opened before they can be 
 * navigated or modified. Opening a textual element (such as a compilation unit)
 * involves opening a buffer on its contents.  While open, any changes to the buffer
 * can be reflected in the element's structure; 
 * see <code>isConsistent</code> and <code>makeConsistent(IProgressMonitor)</code>.
 * <p>
 * To reduce complexity in clients, elements are automatically opened
 * by the EGL model as element properties are accessed. The EGL model maintains
 * an LRU cache of open elements, and automatically closes elements as they
 * are swapped out of the cache to make room for other elements. Elements with
 * unsaved changes are never removed from the cache, and thus, if the client
 * maintains many open elements with unsaved
 * changes, the LRU cache can grow in size (in this case the cache is not
 * bounded). However, as elements are saved, the cache will shrink back to its
 * original bounded size.
 * </p>
 * <p>
 * To open an element, all openable parent elements must be open.
 * The EGL model automatically opens parent elements, as it automatically opens elements.
 * Opening an element may provide access to direct children and other descendants,
 * but does not automatically open any descendents which are themselves <code>IOpenable</code>.
 * For example, opening a compilation unit provides access to all its constituent elements,
 * but opening a package fragment does not open all compilation units in the package fragment.
 * </p>
 * <p>
 * This interface is not intended to be implemented by clients.
 * </p>
 */
public interface IOpenable {

/**
 * Closes this element and its buffer (if any).
 * Closing an element which is not open has no effect.
 *
 * <p>Note: although <code>close</code> is exposed in the API, clients are
 * not expected to open and close elements - the EGL model does this automatically
 * as elements are accessed.
 *
 * @exception EGLModelException if an error occurs closing this element
 */
public void close() throws EGLModelException;
/**
 * Returns the buffer opened for this element, or <code>null</code>
 * if this element does not have a buffer.
 *
 * @exception JavaModelException if this element does not exist or if an
 *		exception occurs while accessing its corresponding resource.
 * @return the buffer opened for this element, or <code>null</code>
 * if this element does not have a buffer
 */
public IBuffer getBuffer() throws EGLModelException;
/**
 * Returns <code>true</code> if this element is open and:
 * <ul>
 * <li>its buffer has unsaved changes, or
 * <li>one of its descendants has unsaved changes, or
 * <li>a working copy has been created on one of this
 * element's children and has not yet destroyed
 * </ul>
 *
 * @exception EGLModelException if this element does not exist or if an
 *		exception occurs while accessing its corresponding resource.
 * @return <code>true</code> if this element is open and:
 * <ul>
 * <li>its buffer has unsaved changes, or
 * <li>one of its descendants has unsaved changes, or
 * <li>a working copy has been created on one of this
 * element's children and has not yet destroyed
 * </ul>
 */
boolean hasUnsavedChanges() throws EGLModelException;
/**
 * Returns whether the element is consistent with its underlying resource or buffer.
 * The element is consistent when opened, and is consistent if the underlying resource
 * or buffer has not been modified since it was last consistent.
 *
 * <p>NOTE: Child consistency is not considered. For example, a package fragment
 * responds <code>true</code> when it knows about all of its
 * compilation units present in its underlying folder. However, one or more of
 * the compilation units could be inconsistent.
 *
 * @exception EGLModelException if this element does not exist or if an
 *		exception occurs while accessing its corresponding resource.
 * @return true if the element is consistent with its underlying resource or buffer, false otherwise.
 * @see IOpenable#makeConsistent
 */
boolean isConsistent() throws EGLModelException;
/**
 * Returns whether this openable is open. This is a handle-only method.
 * @return true if this openable is open, false otherwise
 */
boolean isOpen();
/**
 * Makes this element consistent with its underlying resource or buffer 
 * by updating the element's structure and properties as necessary.
 *
 * @param progress the given progress monitor
 * @exception EGLModelException if the element is unable to access the contents
 * 		of its underlying resource. Reasons include:
 * <ul>
 *  <li>This EGL element does not exist (ELEMENT_DOES_NOT_EXIST)</li>
 * </ul>
 * @see IOpenable#isConsistent
 */
void makeConsistent(IProgressMonitor progress) throws EGLModelException;
/**
 * Opens this element and all parent elements that are not already open.
 * For compilation units, a buffer is opened on the contents of the underlying resource.
 *
 * <p>Note: although <code>open</code> is exposed in the API, clients are
 * not expected to open and close elements - the EGL model does this automatically
 * as elements are accessed.
 *
 * @param progress the given progress monitor
 * @exception EGLModelException if an error occurs accessing the contents
 * 		of its underlying resource. Reasons include:
 * <ul>
 *  <li>This EGL element does not exist (ELEMENT_DOES_NOT_EXIST)</li>
 * </ul>
 */
public void open(IProgressMonitor progress) throws EGLModelException;
/**
 * Saves any changes in this element's buffer to its underlying resource
 * via a workspace resource operation. This has no effect if the element has no underlying
 * buffer, or if there are no unsaved changed in the buffer.
 * <p>
 * The <code>force</code> parameter controls how this method deals with
 * cases where the workbench is not completely in sync with the local file system.
 * If <code>false</code> is specified, this method will only attempt
 * to overwrite a corresponding file in the local file system provided
 * it is in sync with the workbench. This option ensures there is no 
 * unintended data loss; it is the recommended setting.
 * However, if <code>true</code> is specified, an attempt will be made
 * to write a corresponding file in the local file system, 
 * overwriting any existing one if need be.
 * In either case, if this method succeeds, the resource will be marked 
 * as being local (even if it wasn't before).
 * <p>
 * As a result of this operation, the element is consistent with its underlying 
 * resource or buffer. 
 *
 * @param progress the given progress monitor
 * @param force it controls how this method deals with
 * cases where the workbench is not completely in sync with the local file system
 * @exception EGLModelException if an error occurs accessing the contents
 * 		of its underlying resource. Reasons include:
 * <ul>
 *  <li>This EGL element does not exist (ELEMENT_DOES_NOT_EXIST)</li>
 *  <li>This EGL element is read-only (READ_ONLY)</li>
 * </ul>
 */
public void save(IProgressMonitor progress, boolean force) throws EGLModelException;
}
