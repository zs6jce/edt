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
package org.eclipse.edt.ide.ui.internal.refactoring.changes;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.edt.ide.ui.internal.UINlsStrings;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.ChangeDescriptor;
import org.eclipse.ltk.core.refactoring.RefactoringChangeDescriptor;
import org.eclipse.ltk.core.refactoring.RefactoringDescriptor;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;

import com.ibm.icu.text.MessageFormat;

public final class RenameResourceChange extends Change {

	public static IPath renamedResourcePath(IPath path, String newName) {
		return path.removeLastSegments(1).append(newName);
	}

	private final String fComment;

	private final RefactoringDescriptor fDescriptor;

	private final String fNewName;

	private final IPath fResourcePath;

	private final long fStampToRestore;

	private RenameResourceChange(RefactoringDescriptor descriptor, IPath resourcePath, String newName, String comment, long stampToRestore) {
		fDescriptor= descriptor;
		fResourcePath= resourcePath;
		fNewName= newName;
		fComment= comment;
		fStampToRestore= stampToRestore;
	}

	public RenameResourceChange(RefactoringDescriptor descriptor, IResource resource, String newName, String comment) {
		this(descriptor, resource.getFullPath(), newName, comment, IResource.NULL_STAMP);
	}

	private int getCoreRenameFlags() {
		if (getResource().isLinked())
			return IResource.SHALLOW;
		else
			return IResource.NONE;
	}

	public ChangeDescriptor getDescriptor() {
		if (fDescriptor != null)
			return new RefactoringChangeDescriptor(fDescriptor);
		return null;
	}

	public Object getModifiedElement() {
		return getResource();
	}

	public String getName() {
		return MessageFormat.format(UINlsStrings.RenameResourceChange_name, new String[] { fResourcePath.toString(), fNewName});
	}

	public String getNewName() {
		return fNewName;
	}

	private IResource getResource() {
		return ResourcesPlugin.getWorkspace().getRoot().findMember(fResourcePath);
	}

	public RefactoringStatus isValid(IProgressMonitor pm) throws CoreException {
		IResource resource= getResource();
		if (resource == null || !resource.exists()) {
			return RefactoringStatus.createFatalErrorStatus(MessageFormat.format(UINlsStrings.RenameResourceChange_does_not_exist, new String[] {fResourcePath.toString()}));
		}
		return new RefactoringStatus();
	}

	public Change perform(IProgressMonitor pm) throws CoreException {
		try {
			pm.beginTask(UINlsStrings.RenameResourceChange_rename_resource, 1);

			IResource resource= getResource();
			long currentStamp= resource.getModificationStamp();
			IPath newPath= renamedResourcePath(fResourcePath, fNewName);
			resource.move(newPath, getCoreRenameFlags(), pm);
			if (fStampToRestore != IResource.NULL_STAMP) {
				IResource newResource= ResourcesPlugin.getWorkspace().getRoot().findMember(newPath);
				newResource.revertModificationStamp(fStampToRestore);
			}
			String oldName= fResourcePath.lastSegment();
			return new RenameResourceChange(null, newPath, oldName, fComment, currentStamp);
		} finally {
			pm.done();
		}
	}

	public void initializeValidationData(IProgressMonitor pm) {
	}
}
