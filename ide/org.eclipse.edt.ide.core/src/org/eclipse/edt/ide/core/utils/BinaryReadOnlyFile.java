/*******************************************************************************
 * Copyright © 2010, 2013 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *
 *******************************************************************************/
package org.eclipse.edt.ide.core.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URI;
import java.util.Map;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFileState;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IPathVariableManager;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResourceProxy;
import org.eclipse.core.resources.IResourceProxyVisitor;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourceAttributes;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.content.IContentDescription;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.edt.compiler.internal.io.IRFileNameUtility;
import org.eclipse.edt.ide.core.internal.model.util.Util;

public class BinaryReadOnlyFile implements IFile {
	public static final String IR_EXTENSION = ".eglxml";
	public static final String EGLAR_IR_SEPARATOR = "|";
	
	private IPath eglarPath;		// full path for eglar file
	private String sourcePath;	// relative path for the source file (foo.egl)
	private String irName;		// simple name, including extension, of the ir file (bar.eglxml)
	private boolean isExternal;
	private IProject project;
	
	private String irFileFullPath;
	private IPath projectRelativePath;
	private String sourceContent;
	private String eglarPathStr;
	private String eglarSourcePath;
	
	public String getEglarPathStr() {
		return eglarPathStr;
	}

	public String getEglarSourcePath() {
		return eglarSourcePath;
	}
	
	public BinaryReadOnlyFile(String eglarPath, String sourcePath, String irName) {
		this(eglarPath, sourcePath, irName, null, false);
	}
	
	public BinaryReadOnlyFile(String eglarPath, String sourcePath, String irName, IProject project, boolean isExternal) {
		this.eglarPathStr = eglarPath;
		this.eglarSourcePath = sourcePath;
		this.eglarPath = new Path(eglarPath);
		this.sourcePath = resolveFileString(sourcePath);
		this.irName = IRFileNameUtility.toIRFileName(irName);
		this.project = project;
		this.isExternal = isExternal;
		
		if (this.irName.indexOf(".") == -1) {
			this.irName = this.irName + IR_EXTENSION;
		}
	}
	
	public String getFullQualifiedName() {
		return sourcePath;
	}
	
	public String getIrName() {
		return irName;
	}
	
	public InputStream getContents() throws CoreException {
		InputStream fs = null;
		try {
			JarFile jarFile = (new JarFile( eglarPath.toString() ));
			ZipEntry  entry = jarFile.getEntry( sourcePath );
			fs = jarFile.getInputStream( entry );
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fs;
	}
	
	public Object getAdapter(Class adapter) {
		return null;
	}
	
	public String getSource() {
		if(sourceContent == null) {
			ZipFile zip = null;
			sourceContent = "";
			try {
				zip = new ZipFile(eglarPath.toFile());
				ZipEntry ze = new ZipEntry(sourcePath);
				sourceContent = new String(Util.getZipEntryByteContent(ze, zip), this.getCharset());
			} catch (ZipException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		return sourceContent;
	}
	
	public String[] getPackageSegments() {
		if(sourcePath != null) {
			String[] package1 = sourcePath.split( "/" );
			String[] package2 = new String[ package1.length - 1];
			System.arraycopy(package1, 0, package2, 0, package2.length );
			
			return package2;
		} else 
			return null;
		
	}
	
	public IPath getFullPath() {
		return eglarPath;
	}
	
	public String getIrFullPathString() {
		if(irFileFullPath != null) {
			return irFileFullPath;
		} else {
			String irFileFullPath = eglarPath.toString();
			irFileFullPath += EGLAR_IR_SEPARATOR;
			irFileFullPath += sourcePath;
			return irFileFullPath;  
		}
	}
	
	/*
	 * get the package name
	 */
	public String getPackage(){
		int index = sourcePath.lastIndexOf("/");
		if(index > -1){	//has pacakge
			return sourcePath.substring(0, index);
		}
		return "";	//no package
	}
	
	/*
	 * get the simple source file name
	 * e.g.
	 * demointerface.egl
	 * 
	 * We return this value instead of the IR's name because there can be several parts in the same source file and we
	 * have them all display in the same editor, instead of each getting their own editor.
	 */
	public String getName() {
		String irFileName = getProjectRelativePath().lastSegment();
		int index = irFileName.lastIndexOf(".");
		if(index > -1){	//has package
			irFileName = irFileName.substring(0,index) + IR_EXTENSION;
		}
		else{	//no package
			irFileName = irFileName + IR_EXTENSION;
		}
		return irFileName;
	}
	
	public boolean isReadOnly() {
		return true;
	}
	
	public boolean isExternal() {
		return isExternal;
	}
	
	public IProject getProject() {
		return project;
	}
	
	public void setProject(IProject project) {
		this.project = project;
	}
	
	@Override
	public String getFileExtension() {
		return "egl";
	}
	
	@Override
	public IPath getProjectRelativePath() {
		if(projectRelativePath == null) {
			projectRelativePath = new Path(sourcePath);
		}
		return projectRelativePath;
	}
	
	
	//for Forms in FormGroup, to eliminate the Form section, only keep the FormGroup section, which exactly is the ClassFile name
	private static String resolveFileString(String file){
		String fileString = file;
		int index = fileString != null ? fileString.indexOf(":") : -1;
		if(index != -1){
			fileString = fileString.substring(0, index);
		}
		return fileString;
	}
	
	public boolean equals(Object target) {
		if (this == target)
			return true;
		if (!(target instanceof BinaryReadOnlyFile))
			return false;
		BinaryReadOnlyFile resource = (BinaryReadOnlyFile) target;
		
		return getFullPath().equals(resource.getFullPath()) && getProjectRelativePath().equals(resource.getProjectRelativePath());
	}
	
	public int hashCode() {
		return getFullPath().hashCode()+ getProjectRelativePath().hashCode() * 17;
	}
	
	@Override
	public void accept(IResourceProxyVisitor visitor, int memberFlags)
			throws CoreException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void accept(IResourceVisitor visitor) throws CoreException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void accept(IResourceVisitor visitor, int depth,
			boolean includePhantoms) throws CoreException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void accept(IResourceVisitor visitor, int depth, int memberFlags)
			throws CoreException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void clearHistory(IProgressMonitor monitor) throws CoreException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void copy(IPath destination, boolean force, IProgressMonitor monitor)
			throws CoreException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void copy(IPath destination, int updateFlags,
			IProgressMonitor monitor) throws CoreException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void copy(IProjectDescription description, boolean force,
			IProgressMonitor monitor) throws CoreException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void copy(IProjectDescription description, int updateFlags,
			IProgressMonitor monitor) throws CoreException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public IMarker createMarker(String type) throws CoreException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public IResourceProxy createProxy() {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void delete(boolean force, IProgressMonitor monitor)
			throws CoreException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void delete(int updateFlags, IProgressMonitor monitor)
			throws CoreException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void deleteMarkers(String type, boolean includeSubtypes, int depth)
			throws CoreException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public boolean exists() {
		if(getSource() != null)
			return true;
		else		
			return false;
	}
	
	@Override
	public IMarker findMarker(long id) throws CoreException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public IMarker[] findMarkers(String type, boolean includeSubtypes, int depth)
			throws CoreException {
		return new IMarker[0];
	}
	
	@Override
	public int findMaxProblemSeverity(String type, boolean includeSubtypes,
			int depth) throws CoreException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public long getLocalTimeStamp() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public IPath getLocation() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public URI getLocationURI() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public IMarker getMarker(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public long getModificationStamp() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public IPathVariableManager getPathVariableManager() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public IContainer getParent() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Map<QualifiedName, String> getPersistentProperties()
			throws CoreException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getPersistentProperty(QualifiedName key) throws CoreException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public IPath getRawLocation() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public URI getRawLocationURI() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ResourceAttributes getResourceAttributes() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Map<QualifiedName, Object> getSessionProperties()
			throws CoreException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Object getSessionProperty(QualifiedName key) throws CoreException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public IWorkspace getWorkspace() {
		return getProject() != null ?  getProject().getWorkspace() : null;
	}
	
	@Override
	public boolean isAccessible() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isDerived() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isDerived(int options) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isHidden() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isHidden(int options) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isLinked() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isVirtual() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isLinked(int options) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isLocal(int depth) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isPhantom() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isSynchronized(int depth) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isTeamPrivateMember() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isTeamPrivateMember(int options) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void move(IPath destination, boolean force, IProgressMonitor monitor)
			throws CoreException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void move(IPath destination, int updateFlags,
			IProgressMonitor monitor) throws CoreException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void move(IProjectDescription description, boolean force,
			boolean keepHistory, IProgressMonitor monitor) throws CoreException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void move(IProjectDescription description, int updateFlags,
			IProgressMonitor monitor) throws CoreException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void refreshLocal(int depth, IProgressMonitor monitor)
			throws CoreException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void revertModificationStamp(long value) throws CoreException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void setDerived(boolean isDerived) throws CoreException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void setDerived(boolean isDerived, IProgressMonitor monitor)
			throws CoreException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void setHidden(boolean isHidden) throws CoreException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void setLocal(boolean flag, int depth, IProgressMonitor monitor)
			throws CoreException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public long setLocalTimeStamp(long value) throws CoreException {
		return 0;
	}
	
	@Override
	public void setPersistentProperty(QualifiedName key, String value)
			throws CoreException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void setReadOnly(boolean readOnly) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void setResourceAttributes(ResourceAttributes attributes)
			throws CoreException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void setSessionProperty(QualifiedName key, Object value)
			throws CoreException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void setTeamPrivateMember(boolean isTeamPrivate)
			throws CoreException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void touch(IProgressMonitor monitor) throws CoreException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public boolean contains(ISchedulingRule rule) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isConflicting(ISchedulingRule rule) {
		return false;
	}
	
	@Override
	public void appendContents(InputStream source, boolean force,
			boolean keepHistory, IProgressMonitor monitor) throws CoreException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void appendContents(InputStream source, int updateFlags,
			IProgressMonitor monitor) throws CoreException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void create(InputStream source, boolean force,
			IProgressMonitor monitor) throws CoreException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void create(InputStream source, int updateFlags,
			IProgressMonitor monitor) throws CoreException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void createLink(IPath localLocation, int updateFlags,
			IProgressMonitor monitor) throws CoreException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void createLink(URI location, int updateFlags,
			IProgressMonitor monitor) throws CoreException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void delete(boolean force, boolean keepHistory,
			IProgressMonitor monitor) throws CoreException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public String getCharset() throws CoreException {
		return "UTF-8";
	}
	
	@Override
	public String getCharset(boolean checkImplicit) throws CoreException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getCharsetFor(Reader reader) throws CoreException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public IContentDescription getContentDescription() throws CoreException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public InputStream getContents(boolean force) throws CoreException {
		return getContents();
	}
	
	@Override
	public int getEncoding() throws CoreException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public IFileState[] getHistory(IProgressMonitor monitor)
			throws CoreException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void move(IPath destination, boolean force, boolean keepHistory,
			IProgressMonitor monitor) throws CoreException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void setCharset(String newCharset) throws CoreException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void setCharset(String newCharset, IProgressMonitor monitor)
			throws CoreException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void setContents(InputStream source, boolean force,
			boolean keepHistory, IProgressMonitor monitor) throws CoreException {
		throw new UnsupportedOperationException();
		
	}
	
	@Override
	public void setContents(IFileState source, boolean force,
			boolean keepHistory, IProgressMonitor monitor) throws CoreException {
		throw new UnsupportedOperationException();
		
	}
	
	@Override
	public void setContents(InputStream source, int updateFlags,
			IProgressMonitor monitor) throws CoreException {
		throw new UnsupportedOperationException();
		
	}
	
	@Override
	public void setContents(IFileState source, int updateFlags,
			IProgressMonitor monitor) throws CoreException {
		throw new UnsupportedOperationException();
	}
	
}
