/*******************************************************************************
 *  Copyright (c) 2011 IBM Corporation and others.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 * 
 *  Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.cdt.ui.resources;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;

import org.eclipse.cdt.ui.CUIPlugin;

/**
 * <strong>EXPERIMENTAL</strong>. This class or interface has been added as
 * part of a work in progress. There is no guarantee that this API will work or
 * that it will remain the same. Please do not use this API without consulting
 * with the CDT team.
 * 
 * @author crecoskie
 *
 */
public class RefreshExclusionContributionManager {
	
	public static final String EXCLUSION_CONTRIBUTOR = "exclusionContributor"; //$NON-NLS-1$
	public static final String EXTENSION_ID = "RefreshExclusionContributor"; //$NON-NLS-1$
	private Map<String, RefreshExclusionContributor> fIDtoContributorsMap;
	private static RefreshExclusionContributionManager fInstance;
	
	private RefreshExclusionContributionManager() {
		fIDtoContributorsMap = new HashMap<String, RefreshExclusionContributor>();
		loadExtensions();
	}
	
	public static synchronized RefreshExclusionContributionManager getInstance() {
		if(fInstance == null) {
			fInstance = new RefreshExclusionContributionManager();
		}
		
		return fInstance;
	}

	public synchronized void loadExtensions() {
		IExtensionPoint extension = Platform.getExtensionRegistry().getExtensionPoint(CUIPlugin.PLUGIN_ID,
				EXTENSION_ID);
		if (extension != null) {
			IExtension[] extensions = extension.getExtensions();
			for (IExtension extension2 : extensions) {
				IConfigurationElement[] configElements = extension2.getConfigurationElements();
				for (IConfigurationElement configElement : configElements) {

					if (configElement.getName().equals(EXCLUSION_CONTRIBUTOR)) {

						String id = configElement.getAttribute("id"); //$NON-NLS-1$
						String utility = configElement.getAttribute("class"); //$NON-NLS-1$

						if (utility != null) {
							try {
								Object execExt = configElement.createExecutableExtension("class"); //$NON-NLS-1$
								if ((execExt instanceof RefreshExclusionContributor) && id != null) {
									fIDtoContributorsMap.put(id, (RefreshExclusionContributor) execExt);
								}
							} catch (CoreException e) {
								CUIPlugin.log(e);
							}
						}
					}
				}
			}
		}
	}
}