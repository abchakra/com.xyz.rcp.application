package com.xyz.rcp.firstapplication;

import java.util.ArrayList;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IStartup;

public class ApplicationStartupTasks implements IStartup {

	@Override
	public void earlyStartup() {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IResourceChangeListener listener = new IResourceChangeListener() {
			public void resourceChanged(IResourceChangeEvent event) {
				final ArrayList changed = new ArrayList();
				// we are only interested in POST_CHANGE events
				if (event.getType() != IResourceChangeEvent.POST_CHANGE)
					return;
				IResourceDelta rootDelta = event.getDelta();
				
				
				IResourceDeltaVisitor visitor = new IResourceDeltaVisitor() {
					@Override
					public boolean visit(IResourceDelta delta) {
						IResource resource = delta.getResource();
						if (resource.getType() == IResource.FILE
								&& "txt".equalsIgnoreCase(resource.getFileExtension())) {
							changed.add((IFile) resource);
						}
						return true;
					}
				};
				try {
					rootDelta.accept(visitor);
				} catch (CoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				System.out.println("Something changed!" + changed);
			}
		};
		workspace.addResourceChangeListener(listener);

		// ... some time later one ...
		// workspace.removeResourceChangeListener(listener);

	}

}
