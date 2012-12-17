package com.xyz.rcp.firstapplication;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.ui.IStartup;

public class ApplicationStartupTasks implements IStartup {

	@Override
	public void earlyStartup() {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IResourceChangeListener listener = new IResourceChangeListener() {
			public void resourceChanged(IResourceChangeEvent event) {
				System.out.println("Something changed!");
			}
		};
		workspace.addResourceChangeListener(listener);

		// ... some time later one ...
		// workspace.removeResourceChangeListener(listener);

	}

}
