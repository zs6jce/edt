/*******************************************************************************
 * Copyright © 2012 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *
 *******************************************************************************/
package org.eclipse.edt.compiler.binding;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.edt.compiler.internal.core.validation.annotation.AS400TimestampValidator;
import org.eclipse.edt.compiler.internal.core.validation.annotation.AbstractAS400ParameterAnnotaionValidator;
import org.eclipse.edt.mof.egl.utils.InternUtil;

public class AS400TimestampAnnotationTypeBinding extends AS400ParmeterAnnotationTypeBinding {

	private static AS400TimestampAnnotationTypeBinding INSTANCE = new AS400TimestampAnnotationTypeBinding();

	public static final String caseSensitiveName = InternUtil.internCaseSensitive("AS400Timestamp");
	public static final String name = InternUtil.intern(caseSensitiveName);
	
   	private static final List validationAnns = new ArrayList();
   	static{
   		validationAnns.add(new UserDefinedAnnotationValidationRule(AS400TimestampValidator.class));
   	}

   	public AbstractAS400ParameterAnnotaionValidator getValidator() {
   		return new AS400TimestampValidator();
   	}
   	
	public AS400TimestampAnnotationTypeBinding() {
		super(caseSensitiveName);
	}
	
	public static AS400TimestampAnnotationTypeBinding getInstance() {
		return INSTANCE;
	}


	@Override
	public boolean isApplicableFor(IBinding binding) {
		return true;
	}
	
	public List getAnnotations(){
		return validationAnns;
	}

	

}