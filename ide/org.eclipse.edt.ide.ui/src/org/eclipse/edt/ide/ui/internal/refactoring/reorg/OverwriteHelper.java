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
package org.eclipse.edt.ide.ui.internal.refactoring.reorg;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.edt.ide.core.model.IEGLElement;
import org.eclipse.edt.ide.core.model.IEGLFile;
import org.eclipse.edt.ide.core.model.IEGLProject;
import org.eclipse.edt.ide.core.model.IPackageFragment;
import org.eclipse.edt.ide.core.model.IPackageFragmentRoot;
import org.eclipse.edt.ide.ui.internal.UINlsStrings;

import com.ibm.icu.text.MessageFormat;

class OverwriteHelper {
	private Object fDestination;
	private IFile[] fFiles= new IFile[0];
	private IFolder[] fFolders= new IFolder[0];
	private IEGLFile[] fCus= new IEGLFile[0];
	private IPackageFragmentRoot[] fRoots= new IPackageFragmentRoot[0];
	private IPackageFragment[] fPackageFragments= new IPackageFragment[0];

	public void setFiles(IFile[] files) {
		Assert.isNotNull(files);
		fFiles= files;
	}

	public void setFolders(IFolder[] folders) {
		Assert.isNotNull(folders);
		fFolders= folders;
	}

	public void setCus(IEGLFile[] cus) {
		Assert.isNotNull(cus);
		fCus= cus;
	}
	
	public void setPackageFragmentRoots(IPackageFragmentRoot[] roots) {
		Assert.isNotNull(roots);
		fRoots= roots;
	}	

	public void setPackages(IPackageFragment[] fragments) {
		Assert.isNotNull(fragments);
		fPackageFragments= fragments;
	}

	public IFile[] getFilesWithoutUnconfirmedOnes() {
		return fFiles;
	}

	public IFolder[] getFoldersWithoutUnconfirmedOnes() {
		return fFolders;
	}

	public IEGLFile[] getCusWithoutUnconfirmedOnes() {
		return fCus;
	}

	public IPackageFragmentRoot[] getPackageFragmentRootsWithoutUnconfirmedOnes() {
		return fRoots;
	}

	public IPackageFragment[] getPackagesWithoutUnconfirmedOnes() {
		return fPackageFragments;
	}

	public void confirmOverwritting(IReorgQueries reorgQueries, IEGLElement destination) {
		Assert.isNotNull(destination);
		fDestination= destination;
		confirmOverwritting(reorgQueries);
	}

	public void confirmOverwritting(IReorgQueries reorgQueries, IResource destination) {
		Assert.isNotNull(destination);
		Assert.isNotNull(reorgQueries);
		fDestination= destination;
		confirmOverwritting(reorgQueries);
	}
	
	private void confirmOverwritting(IReorgQueries reorgQueries) {
		IConfirmQuery overwriteQuery= reorgQueries.createYesYesToAllNoNoToAllQuery(UINlsStrings.OverwriteHelper_0, true, IReorgQueries.CONFIRM_OVERWRITTING); 
		IConfirmQuery skipQuery= reorgQueries.createSkipQuery(UINlsStrings.OverwriteHelper_2, IReorgQueries.CONFIRM_SKIPPING); 
		confirmFileOverwritting(overwriteQuery);
		confirmFolderOverwritting(skipQuery);
		confirmCuOverwritting(overwriteQuery);	
		confirmPackageFragmentRootOverwritting(skipQuery);	
		confirmPackageOverwritting(overwriteQuery);	
	}

	private void confirmPackageFragmentRootOverwritting(IConfirmQuery overwriteQuery) {
		List toNotOverwrite= new ArrayList(1);
		for (int i= 0; i < fRoots.length; i++) {
			IPackageFragmentRoot root= fRoots[i];
			if (canOverwrite(root) && ! skip(root.getElementName(), overwriteQuery))
				toNotOverwrite.add(root);
		}
		IPackageFragmentRoot[] roots= (IPackageFragmentRoot[]) toNotOverwrite.toArray(new IPackageFragmentRoot[toNotOverwrite.size()]);
		fRoots= ArrayTypeConverter.toPackageFragmentRootArray(ReorgUtils.setMinus(fRoots, roots));
	}

	private void confirmCuOverwritting(IConfirmQuery overwriteQuery) {
		List cusToNotOverwrite= new ArrayList(1);
		for (int i= 0; i < fCus.length; i++) {
			IEGLFile cu= fCus[i];
			if (canOverwrite(cu) && ! overwrite(cu, overwriteQuery))
				cusToNotOverwrite.add(cu);
		}
		IEGLFile[] cus= (IEGLFile[]) cusToNotOverwrite.toArray(new IEGLFile[cusToNotOverwrite.size()]);
		fCus= ArrayTypeConverter.toEGLFileArray(ReorgUtils.setMinus(fCus, cus));
	}

	private void confirmFolderOverwritting(IConfirmQuery overwriteQuery) {
		List foldersToNotOverwrite= new ArrayList(1);
		for (int i= 0; i < fFolders.length; i++) {
			IFolder folder= fFolders[i];
			if (canOverwrite(folder) && ! skip(folder.getName(), overwriteQuery))
				foldersToNotOverwrite.add(folder);				
		}
		IFolder[] folders= (IFolder[]) foldersToNotOverwrite.toArray(new IFolder[foldersToNotOverwrite.size()]);
		fFolders= ArrayTypeConverter.toFolderArray(ReorgUtils.setMinus(fFolders, folders));
	}

	private void confirmFileOverwritting(IConfirmQuery overwriteQuery) {
		List filesToNotOverwrite= new ArrayList(1);
		for (int i= 0; i < fFiles.length; i++) {
			IFile file= fFiles[i];
			if (canOverwrite(file) && ! overwrite(file, overwriteQuery))
				filesToNotOverwrite.add(file);
		}
		IFile[] files= (IFile[]) filesToNotOverwrite.toArray(new IFile[filesToNotOverwrite.size()]);
		fFiles= ArrayTypeConverter.toFileArray(ReorgUtils.setMinus(fFiles, files));
	}

	private void confirmPackageOverwritting(IConfirmQuery overwriteQuery){
		List toNotOverwrite= new ArrayList(1);
		for (int i= 0; i < fPackageFragments.length; i++) {
			IPackageFragment pack= fPackageFragments[i];
			if (canOverwrite(pack) && ! overwrite(pack, overwriteQuery))
				toNotOverwrite.add(pack);
		}
		IPackageFragment[] packages= (IPackageFragment[]) toNotOverwrite.toArray(new IPackageFragment[toNotOverwrite.size()]);
		fPackageFragments= ArrayTypeConverter.toPackageArray(ReorgUtils.setMinus(fPackageFragments, packages));
	}

	private boolean canOverwrite(IPackageFragment pack) {
		Assert.isTrue(fDestination instanceof IPackageFragmentRoot);
		IPackageFragmentRoot destination= (IPackageFragmentRoot)fDestination;
		return ! destination.equals(pack.getParent()) && destination.getPackageFragment(pack.getElementName()).exists();
	}

	private boolean canOverwrite(IResource resource) {
		if (resource == null)
			return false;
		IResource destinationResource= ReorgUtils.getResource(fDestination);
		if (destinationResource.equals(resource.getParent()))
			return false;
		if (destinationResource instanceof IContainer) {
			IContainer container= (IContainer)destinationResource;
			IResource member=  container.findMember(resource.getName());
			if (member == null || !member.exists())
				return false;
			if (member instanceof IContainer) {
				try {
					if (((IContainer)member).members().length == 0)
						return false;
				} catch (CoreException e) {
					return true;
				}
			}
			return true;
		}
		return false;
	}
	
	private boolean canOverwrite(IPackageFragmentRoot root) {
		Assert.isTrue(fDestination instanceof IEGLProject);
		IEGLProject destination= (IEGLProject)fDestination;
		IFolder conflict= destination.getProject().getFolder(root.getElementName());
		try {
			return !destination.equals(root.getParent()) && conflict.exists() &&  conflict.members().length > 0;
		} catch (CoreException e) {
			return true;
		}
	}

	private boolean canOverwrite(IEGLFile cu) {
		if (fDestination instanceof IPackageFragment){
			IPackageFragment destination= (IPackageFragment)fDestination;
			return ! destination.equals(cu.getParent()) && destination.getEGLFile(cu.getElementName()).exists();
		} else {
			return canOverwrite(ReorgUtils.getResource(cu));
		}
	}

	private static boolean overwrite(IResource resource, IConfirmQuery overwriteQuery){
		return overwrite(resource.getName(), overwriteQuery);
	}

	private static boolean overwrite(IEGLElement element, IConfirmQuery overwriteQuery){
		return overwrite(element.getElementName(), overwriteQuery);
	}

	private static boolean overwrite(String name, IConfirmQuery overwriteQuery){
		String question= MessageFormat.format(UINlsStrings.OverwriteHelper_1, new String[] {name}); 
		return overwriteQuery.confirm(question);
	}
	private static boolean skip(String name, IConfirmQuery overwriteQuery){
		String question= MessageFormat.format(UINlsStrings.OverwriteHelper_3, new String[] {name}); 
		return overwriteQuery.confirm(question);
	}	
}
