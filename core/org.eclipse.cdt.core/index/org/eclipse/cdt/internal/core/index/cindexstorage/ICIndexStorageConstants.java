/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.cdt.internal.core.index.cindexstorage;

/**
 * This interface provides constants used by the search engine.
 */
public interface ICIndexStorageConstants {
	/**
	 * The signature of the index file.
	 */
	public static final String SIGNATURE= "INDEX FILE 0.016"; //$NON-NLS-1$
	/**
	 * The separator for files in the index file.
	 */
	public static final char FILE_SEPARATOR= '/';
	/**
	 * The size of a block for a <code>Block</code>.
	 */
	public static final int BLOCK_SIZE= 8192;
	
	/**
	 * Encodings used in CIndexStorage - can be used by clients to encode
	 * strings destined for the index. Proper format is:
	 * 
	 * {encodings + encodingTypes} 
	 * 
	 * for everything except type strings which should look like:
	 * 
	 * {encodings + encodingTypes + typeConstants + '/'}.
	 */
	final static int TYPE = 1;
	final static int FUNCTION = 2;
	final static int METHOD = 3;
	final static int FIELD = 4;
	final static int MACRO = 5;
	final static int NAMESPACE = 6;
	final static int ENUMTOR = 7;
	final static int INCLUDE = 8;

    /**
	 * Encoding types
	 */
    final static int DECLARATION = 1;
    final static int REFERENCE = 2;
    final static int DEFINITION = 3;
    
    /**
     * Encoding constants used in CIndexStorage
     */
	final static int TYPE_CLASS = 1;
	final static int TYPE_STRUCT = 2;
	final static int TYPE_UNION = 3;
	final static int TYPE_ENUM = 4;
	final static int TYPE_VAR = 5;
	final static int TYPE_TYPEDEF = 6;
	final static int TYPE_DERIVED = 7;
	final static int TYPE_FRIEND = 8;
	final static int TYPE_FWD_CLASS = 9;
	final static int TYPE_FWD_STRUCT = 10;
	final static int TYPE_FWD_UNION = 11;

    //Used for offsets
    final static int LINE=1;
    final static int OFFSET=2;
}
