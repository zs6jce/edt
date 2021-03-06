/*******************************************************************************
 * Copyright © 2005, 2013 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *
 *******************************************************************************/
package org.eclipse.edt.compiler.internal.core.lookup;

import java.util.List;

import org.eclipse.edt.compiler.binding.IPackageBinding;
import org.eclipse.edt.compiler.internal.util.BindingUtil;
import org.eclipse.edt.mof.egl.Enumeration;
import org.eclipse.edt.mof.egl.Member;
import org.eclipse.edt.mof.egl.Type;

public class EnumerationScope extends Scope {
	private Enumeration enumeration;
    public EnumerationScope(Scope parentScope, Enumeration enumeration) {
        super(parentScope);
        this.enumeration = enumeration;
    }

    public List<Member> findMember(String simpleName) {
    	List<Member> result = BindingUtil.findMembers(enumeration, simpleName);
        if(result != null) return result;
        
        return parentScope.findMember(simpleName);
    }

    public IPackageBinding findPackage(String simpleName) {
        return parentScope.findPackage(simpleName);
    }

    public List<Type> findType(String simpleName) {
        return parentScope.findType(simpleName);
    }
    
    public Enumeration getPart() {
    	return enumeration;
    }
    
    @Override
    public Type getType() {
    	return enumeration;
    }
}
