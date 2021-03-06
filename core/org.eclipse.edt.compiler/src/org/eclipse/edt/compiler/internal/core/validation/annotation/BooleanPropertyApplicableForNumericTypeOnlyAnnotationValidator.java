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
package org.eclipse.edt.compiler.internal.core.validation.annotation;

import org.eclipse.edt.compiler.core.ast.Node;
import org.eclipse.edt.compiler.internal.core.builder.IProblemRequestor;
import org.eclipse.edt.mof.egl.Annotation;
import org.eclipse.edt.mof.egl.Type;


/**
 * @author svihovec
 */
public class BooleanPropertyApplicableForNumericTypeOnlyAnnotationValidator extends PropertyApplicableForNumericTypeOnlyAnnotationValidator {
	
	public BooleanPropertyApplicableForNumericTypeOnlyAnnotationValidator(String canonicalAnnotationName) {
		super(canonicalAnnotationName);
	}
	
	@Override
	protected void validateType(final Node errorNode, final Annotation annotationBinding, final IProblemRequestor problemRequestor, Type type, String canonicalItemName) {
		if(annotationBinding.getValue() instanceof Boolean && ((Boolean)annotationBinding.getValue()).booleanValue()){
			super.validateType(errorNode, annotationBinding, problemRequestor, type, canonicalItemName);
		}
	}
}
