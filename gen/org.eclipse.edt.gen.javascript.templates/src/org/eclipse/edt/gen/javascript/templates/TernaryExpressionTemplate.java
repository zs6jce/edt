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
import org.eclipse.edt.mof.egl.TernaryExpression;

public class TernaryExpressionTemplate extends JavaScriptTemplate 
{
	public void genExpression( TernaryExpression expr, Context ctx, TabbedWriter out )
	{
		out.print( "((" );
		ctx.invoke( genExpression, expr.getFirst(), ctx, out );
		out.print( ")?(" );
		ctx.invoke( genExpression, expr.getSecond(), ctx, out );
		out.print( "):(" );
		ctx.invoke( genExpression, expr.getThird(), ctx, out );
		out.print( "))" );
	}
}
