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
package org.eclipse.edt.gen.javascript.templates;

import org.eclipse.edt.gen.javascript.Context;
import org.eclipse.edt.mof.codegen.api.TabbedWriter;
import org.eclipse.edt.mof.egl.Function;
import org.eclipse.edt.mof.egl.Member;
import org.eclipse.edt.mof.egl.MemberAccess;
import org.eclipse.edt.mof.egl.MemberName;
import org.eclipse.edt.mof.egl.Type;

public class MemberAccessTemplate extends JavaScriptTemplate {

	public void genExpression(MemberAccess expr, Context ctx, TabbedWriter out) {
		Member member = expr.getMember();
		if (member != null && member.getContainer() != null && member.getContainer() instanceof Type)
			ctx.invoke(genContainerBasedMemberAccess, (Type) member.getContainer(), ctx, out, expr, member);
		else
			genMemberAccess(expr, ctx, out);
	}

	public void genCallbackAccesor(MemberAccess expr, Context ctx, TabbedWriter out) {
		ctx.invoke(genQualifier, expr.getMember(), ctx, out);
		ctx.invoke(genExpression, expr, ctx, out);
	}
	
	public Function getCallbackFunction(MemberName expr, Context ctx) {
		return (Function)ctx.invoke(getCallbackFunction, expr.getMember(), ctx);
	}
	
	public void genMemberAccess(MemberAccess expr, Context ctx, TabbedWriter out) {
		// if this is a delegate...
		if (expr.getMember() instanceof Function)
			if (expr.getQualifier() instanceof MemberName) {
				ctx.invoke(genCallbackAccesor, expr.getQualifier(), ctx, out, expr.getMember());
			} else {
				ctx.invoke(genAccessor, expr.getMember(), ctx, out);
			}
		else {
			ctx.invoke(genExpression, expr.getQualifier(), ctx, out, expr.getQualifier());
			out.print(".");
			ctx.invoke(genAccessor, expr.getMember(), ctx, out);
		}
	}
}
