/*******************************************************************************
 * Copyright © 2011 IBM Corporation and others.
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

import org.eclipse.edt.compiler.binding.AS400UnsignedBin4AnnotationTypeBinding;

public class AS400UnsignedBin4Validator extends AS400BigIntValidator implements IAnnotationValidationRule {
	
	@Override
	protected String getName() {
		return AS400UnsignedBin4AnnotationTypeBinding.caseSensitiveName;
	}
	
	@Override
	protected String getInternedName() {
		return AS400UnsignedBin4AnnotationTypeBinding.name;
	}
}