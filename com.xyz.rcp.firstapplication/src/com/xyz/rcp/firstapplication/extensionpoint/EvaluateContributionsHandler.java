package com.xyz.rcp.firstapplication.extensionpoint;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SafeRunner;

public class EvaluateContributionsHandler extends AbstractHandler {
	private static final String IGREETER_ID = "com.xyz.rcp.firstapplication.greeterExtension";

	private void executeExtension(final Object o) {
		ISafeRunnable runnable = new ISafeRunnable() {
			@Override
			public void handleException(Throwable e) {
				System.out.println("Exception in client");
			}

			@Override
			public void run() throws Exception {
				((IGreeter) o).greet();
			}
		};
		SafeRunner.run(runnable);
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IExtensionPoint activityDesignExtensionPoint = registry
				.getExtensionPoint(IGREETER_ID);

		// get all extensions of "com.tibco.bw.design.ActivityType" extension
		// point
		IExtension[] allExtensions = activityDesignExtensionPoint
				.getExtensions();
		for (IExtension anExtension : allExtensions) {
			IConfigurationElement[] config = anExtension
					.getConfigurationElements();

			try {
				for (IConfigurationElement e : config) {
					System.out.println("Evaluating extension");
					final Object o = e.createExecutableExtension("class");
					if (o instanceof IGreeter) {
						executeExtension(o);
					}
				}
			} catch (CoreException ex) {
				System.out.println(ex.getMessage());
			}
		}
		return null;
	}
}