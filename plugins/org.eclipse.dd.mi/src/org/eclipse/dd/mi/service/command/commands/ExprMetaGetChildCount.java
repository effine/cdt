/*******************************************************************************
 * Copyright (c) 2007 Ericsson and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Ericsson           - initial API and implementation
 *******************************************************************************/
package org.eclipse.dd.mi.service.command.commands;

import org.eclipse.dd.dsf.debug.service.IExpressions.IExpressionDMContext;
import org.eclipse.dd.mi.service.command.output.ExprMetaGetChildCountInfo;

public class ExprMetaGetChildCount extends ExprMetaCommand<ExprMetaGetChildCountInfo> {

	public ExprMetaGetChildCount(IExpressionDMContext ctx) {
		super(ctx);
	}
}