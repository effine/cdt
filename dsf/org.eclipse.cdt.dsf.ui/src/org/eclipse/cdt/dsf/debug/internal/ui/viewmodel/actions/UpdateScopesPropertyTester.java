/*******************************************************************************
 * Copyright (c) 2008, 2009 Wind River Systems and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Wind River Systems - initial API and implementation
 *******************************************************************************/
package org.eclipse.cdt.dsf.debug.internal.ui.viewmodel.actions;

import org.eclipse.cdt.dsf.debug.internal.ui.viewmodel.update.provisional.ICachingVMProviderExtension;
import org.eclipse.cdt.dsf.debug.internal.ui.viewmodel.update.provisional.IVMUpdateScope;
import org.eclipse.cdt.dsf.debug.ui.viewmodel.actions.VMHandlerUtils;
import org.eclipse.cdt.dsf.ui.viewmodel.IVMContext;
import org.eclipse.cdt.dsf.ui.viewmodel.IVMProvider;
import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.debug.ui.IDebugView;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Property tester for update scope information available through the given 
 * object.  The object being tested should be either an {@link IVMContext}, 
 * through which an instance of {@link ICachingVMProviderExtension} could be obtained.
 * Or it could be an {@link IWorkbenchPart}, which is tested to see if it
 * is a debug view through which a caching VM provider can be obtained.  
 * The Caching View Model provider is used to test the given property.
 * <p>
 * Three properties are supported:
 * <ul>
 * <li> "areUpdateScopesSupported" - Checks whether update scopes are 
 * available at all given the receiver.</li>
 * <li> "isUpdateScopeAvailable" - Checks whether the update scope in the 
 * expected value is available for the given receiver.</li>
 * <li> "isUpdateScopeActive" - Checks whether the scope given in the expected 
 * value is the currently active scope for the given receiver.</li>
 * </ul>
 * </p>
 */
public class UpdateScopesPropertyTester extends PropertyTester {

    private static final String SUPPORTED = "areUpdateScopesSupported"; //$NON-NLS-1$
    private static final String AVAILABLE = "isUpdateScopeAvailable"; //$NON-NLS-1$
    private static final String ACTIVE = "isUpdateScopeActive"; //$NON-NLS-1$

    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        if (receiver instanceof IVMContext) {
            IVMProvider provider = ((IVMContext)receiver).getVMNode().getVMProvider();
            if (provider instanceof ICachingVMProviderExtension) {
                return testProvider((ICachingVMProviderExtension)provider, property, expectedValue);
            }
        } else if (receiver instanceof IDebugView) {
            IVMProvider provider = VMHandlerUtils.getVMProviderForPart((IDebugView)receiver);
            if (provider instanceof ICachingVMProviderExtension) {
                return testProvider((ICachingVMProviderExtension)provider, property, expectedValue);                    
            }
        }
        return false;
    }

    private boolean testProvider(ICachingVMProviderExtension provider, String property, Object expectedValue) {
        if (SUPPORTED.equals(property)) {
            return true;
        } else if (AVAILABLE.equals(property)) {
            for (IVMUpdateScope scope : provider.getAvailableUpdateScopes()) {
                if (scope.getID().equals(expectedValue)) {
                    return true;
                }
                return false;
            }
        } else if (ACTIVE.equals(property)) {
            return expectedValue != null && expectedValue.equals(provider.getActiveUpdateScope().getID());
        } 
        return false;
    }
    
}
