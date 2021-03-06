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

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.edt.ide.core.model.EGLCore;
import org.eclipse.edt.ide.core.model.EGLModelException;
import org.eclipse.edt.ide.core.model.IEGLElement;
import org.eclipse.edt.ide.ui.EDTUIPlugin;
import org.eclipse.edt.ide.ui.internal.UINlsStrings;
import org.eclipse.edt.ide.ui.internal.packageexplorer.EGLElementLabelProvider;
import org.eclipse.edt.ide.ui.internal.packageexplorer.EGLElementSorter;
import org.eclipse.edt.ide.ui.internal.viewsupport.EGLElementLabels;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.ui.refactoring.UserInputWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import com.ibm.icu.text.MessageFormat;

abstract class ReorgUserInputPage extends UserInputWizardPage{
	private static final int LABEL_FLAGS= EGLElementLabels.ALL_DEFAULT
			| EGLElementLabels.M_PRE_RETURNTYPE | EGLElementLabels.M_PARAMETER_NAMES | EGLElementLabels.F_PRE_TYPE_SIGNATURE;
	private TreeViewer fViewer;
	public ReorgUserInputPage(String pageName) {
		super(pageName);			
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(Composite parent) {
		initializeDialogUnits(parent);
		Composite result= new Composite(parent, SWT.NONE);
		setControl(result);
		result.setLayout(new GridLayout());
		
		Object initialSelection= getInitiallySelectedElement();
		verifyDestination(initialSelection, true);

		addLabel(result);
		
		fViewer= createViewer(result);
		fViewer.setSelection(new StructuredSelection(initialSelection), true);
		fViewer.addSelectionChangedListener(new ISelectionChangedListener(){
			public void selectionChanged(SelectionChangedEvent event) {
				ReorgUserInputPage.this.viewerSelectionChanged(event);
			}
		});
		Dialog.applyDialogFont(result);
	}
	
	protected Control addLabel(Composite parent) {
		Label label= new Label(parent, SWT.NONE);
		String text;
		int resources= getResources().length;
		int eglElements= getEGLElements().length;

		if (resources == 0 && eglElements == 1) {
			text= MessageFormat.format(
					UINlsStrings.ReorgUserInputPage_choose_destination_single, 
					new String[] {EGLElementLabels.getElementLabel(getEGLElements()[0], LABEL_FLAGS)});
		} else if (resources == 1 && eglElements == 0) {
			text= MessageFormat.format(
					UINlsStrings.ReorgUserInputPage_choose_destination_single, 
					new String[] {getResources()[0].getName()});
		} else {
			text= MessageFormat.format(
					UINlsStrings.ReorgUserInputPage_choose_destination_multi, 
					new String[] {String.valueOf(resources + eglElements)});
		}

		label.setText(text);
		label.setLayoutData(new GridData(SWT.BEGINNING, SWT.END, false, false));
		return label;
	}
	
	private void viewerSelectionChanged(SelectionChangedEvent event) {
		ISelection selection= event.getSelection();
		if (!(selection instanceof IStructuredSelection))
			return;
		IStructuredSelection ss= (IStructuredSelection)selection;
		verifyDestination(ss.getFirstElement(), false);
	}
	
	protected abstract Object getInitiallySelectedElement();
	
	/** Set and verify destination */
	protected abstract RefactoringStatus verifyDestination(Object selected) throws EGLModelException;
	
	protected abstract IResource[] getResources();
	protected abstract IEGLElement[] getEGLElements();

	protected abstract IReorgDestinationValidator getDestinationValidator();
	
	private final void verifyDestination(Object selected, boolean initialVerification) {
		try {
			RefactoringStatus status= verifyDestination(selected);
			if (initialVerification)
				setPageComplete(status.isOK());
			else
				setPageComplete(status);
		} catch (EGLModelException e) {
			EDTUIPlugin.log(e);
			setPageComplete(false);
		}
	}		

	private TreeViewer createViewer(Composite parent) {
		TreeViewer treeViewer= new TreeViewer(parent, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		GridData gd= new GridData(GridData.FILL_BOTH);
		gd.widthHint= convertWidthInCharsToPixels(40);
		gd.heightHint= convertHeightInCharsToPixels(15);
		treeViewer.getTree().setLayoutData(gd);
		treeViewer.setLabelProvider(new EGLElementLabelProvider(EGLElementLabelProvider.SHOW_SMALL_ICONS));
		treeViewer.setContentProvider(new DestinationContentProvider(getDestinationValidator()));
		treeViewer.setSorter(new EGLElementSorter());
		treeViewer.setInput(EGLCore.create(ResourcesPlugin.getWorkspace().getRoot()));
		return treeViewer;
	}
	
	protected TreeViewer getTreeViewer() {
		return fViewer;
	}
}
