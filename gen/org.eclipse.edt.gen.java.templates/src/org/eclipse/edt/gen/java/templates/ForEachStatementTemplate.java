/*******************************************************************************
 * Copyright © 2011, 2012 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *
 *******************************************************************************/
package org.eclipse.edt.gen.java.templates;

import org.eclipse.edt.gen.Label;
import org.eclipse.edt.gen.java.Context;
import org.eclipse.edt.mof.codegen.api.TabbedWriter;
import org.eclipse.edt.mof.egl.ForEachStatement;

public class ForEachStatementTemplate extends JavaTemplate {

	public void genStatementBody(ForEachStatement stmt, Context ctx, TabbedWriter out) {
		// start the foreach statement
		out.println("{");
		ctx.invoke(genDeclarationExpression, stmt.getDeclarationExpression(), ctx, out);
		// we need to process this as a simple for statement
		Label label = new Label(ctx, Label.LABEL_TYPE_FOR);
		ctx.pushLabelStack(label);
		if (ctx.getAttribute(stmt, org.eclipse.edt.gen.Constants.SubKey_statementNeedsLabel) != null
			&& ((Boolean) ctx.getAttribute(stmt, org.eclipse.edt.gen.Constants.SubKey_statementNeedsLabel)).booleanValue())
			out.print(label.getName() + ": ");
		out.print("while (");
		ctx.invoke(genName, stmt.getDeclarationExpression().getFields().get(0), ctx, out);
		out.print("++ < ");
		ctx.invoke(genMemberName, stmt.getDataSource(), ctx, out);
		out.println(".size()) {");
		// now process the statement block
		ctx.invoke(genStatement, stmt.getBody(), ctx, out);
		// finish the foreach while loop
		out.println("}");
		// finish the foreach statement
		out.println("}");
		// now remove the label from the stack
		ctx.popLabelStack();
	}

	public void genStatementEnd(ForEachStatement stmt, Context ctx, TabbedWriter out) {
		// we don't want a semi-colon
	}
}