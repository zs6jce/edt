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
package org.eclipse.edt.gen.javascript.annotation.templates;

import org.eclipse.edt.compiler.core.IEGLConstants;
import org.eclipse.edt.gen.javascript.Constants;
import org.eclipse.edt.gen.javascript.Context;
import org.eclipse.edt.gen.javascript.templates.JavaScriptTemplate;
import org.eclipse.edt.mof.egl.Annotation;
import org.eclipse.edt.mof.egl.AnnotationType;
import org.eclipse.edt.mof.egl.Assignment;
import org.eclipse.edt.mof.egl.AssignmentStatement;
import org.eclipse.edt.mof.egl.ExternalType;
import org.eclipse.edt.mof.egl.Field;
import org.eclipse.edt.mof.egl.MemberName;
import org.eclipse.edt.mof.egl.PartName;
import org.eclipse.edt.mof.egl.QualifiedFunctionInvocation;
import org.eclipse.edt.mof.egl.StatementBlock;
import org.eclipse.edt.mof.egl.StringLiteral;
import org.eclipse.edt.mof.egl.utils.TypeUtils;

public class ResourceTemplate extends JavaScriptTemplate {

	public void preGen(AnnotationType type, Context ctx, Annotation annot, Field field){
		AssignmentStatement assignmentStatement = ctx.getFactory().createAssignmentStatement();
		if (field.getAnnotation(IEGLConstants.EGL_LOCATION) != null)
			assignmentStatement.addAnnotation(field.getAnnotation(IEGLConstants.EGL_LOCATION));
		assignmentStatement.setContainer(field.getContainer());
		Assignment assignment = factory.createAssignment();
		if (field.getAnnotation(IEGLConstants.EGL_LOCATION) != null)
			assignment.addAnnotation(field.getAnnotation(IEGLConstants.EGL_LOCATION));
		assignmentStatement.setAssignment(assignment);
		MemberName nameExpression = factory.createMemberName();
		if (field.getAnnotation(IEGLConstants.EGL_LOCATION) != null)
			nameExpression.addAnnotation(field.getAnnotation(IEGLConstants.EGL_LOCATION));
		nameExpression.setMember(field);
		nameExpression.setId(field.getCaseSensitiveName());
		assignment.setLHS(nameExpression);
		assignment.setRHS(getLibraryInvocation(annot, field));
		// add the assignment to the declaration statement block
		StatementBlock declarationBlock = field.getInitializerStatements();
		if(declarationBlock == null){
			declarationBlock = factory.createStatementBlock();
			declarationBlock.setContainer(field.getContainer());
			field.setInitializerStatements(declarationBlock);
		}
		declarationBlock.getStatements().add(declarationBlock.getStatements().size(), assignmentStatement);
	}
	
	private QualifiedFunctionInvocation getLibraryInvocation( Annotation annot, Field field) {
		ExternalType serviceLib = (ExternalType)TypeUtils.getType(TypeUtils.EGL_KeyScheme + Constants.Resources).clone();
		QualifiedFunctionInvocation invocation = factory.createQualifiedFunctionInvocation();
		PartName partName  = factory.createPartName();
		partName.setType(serviceLib);
		invocation.setId("getResource");
		invocation.setQualifier(partName);
		StringLiteral uri = factory.createStringLiteral();
		invocation.getArguments().add(uri);
		if(annot.getValue(Constants.SubKey_uri) instanceof String && !((String)annot.getValue(Constants.SubKey_uri)).isEmpty()){
			uri.setValue((String)annot.getValue(Constants.SubKey_uri));
		}
		else{
			uri.setValue("binding:" + field.getCaseSensitiveName());
		}
		return invocation;
	}
}
