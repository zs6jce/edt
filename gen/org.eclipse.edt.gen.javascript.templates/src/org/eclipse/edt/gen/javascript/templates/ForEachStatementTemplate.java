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

import org.eclipse.edt.gen.Label;
import org.eclipse.edt.gen.javascript.Context;
import org.eclipse.edt.mof.codegen.api.TabbedWriter;
import org.eclipse.edt.mof.egl.ArrayType;
import org.eclipse.edt.mof.egl.ForEachStatement;

public class ForEachStatementTemplate extends JavaScriptTemplate {

	public void genStatementBody(ForEachStatement stmt, Context ctx, TabbedWriter out) {
		// we need to process this as a simple for statement
		Label label = new Label(ctx, Label.LABEL_TYPE_FOR);
		ctx.pushLabelStack(label);
		if (ctx.getAttribute(stmt, org.eclipse.edt.gen.Constants.SubKey_statementNeedsLabel) != null
			&& ((Boolean) ctx.getAttribute(stmt, org.eclipse.edt.gen.Constants.SubKey_statementNeedsLabel)).booleanValue())
			out.print(label.getName() + ": ");
		ctx.invoke(genExpression, stmt.getDataSource(), ctx, out, stmt.getDataSource());
		out.print(".forEach(function( ");
		ctx.invoke(genName, stmt.getDeclarationExpression().getFields().get(0), ctx, out);
		out.println(" ){");
		// as we have an implicit assignment, we need to check to see if a null check is needed
		if (!stmt.getDeclarationExpression().getFields().get(0).isNullable()
			&& (stmt.getDataSource().getType() instanceof ArrayType && ((ArrayType) stmt.getDataSource().getType()).elementsNullable())) {
			out.print("egl.checkNull(");
			ctx.invoke(genName, stmt.getDeclarationExpression().getFields().get(0), ctx, out);
			out.println(");");
		}
		// now process the statement block
		ctx.invoke(genStatementNoBraces, stmt.getBody(), ctx, out);
		out.println("}, this );");
		// now remove the label from the stack
		ctx.popLabelStack();
	}

	public void genStatementEnd(ForEachStatement stmt, Context ctx, TabbedWriter out) {
		// we don't want a semi-colon
	}
}
