package com.xyz.rcp.firstapplication.resourcesapi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;

public class ResourceAPIExample extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkspaceRoot myWorkspaceRoot = ResourcesPlugin.getWorkspace()
				.getRoot();

		IProject myWebProject = myWorkspaceRoot.getProject("com.xyz.rcp.firstapplication");
		// open if necessary
		if (myWebProject.exists() && !myWebProject.isOpen()) {
			try {
				myWebProject.open(null);

				IFolder imagesFolder = myWebProject.getFolder("images");
				if (imagesFolder.exists()) {
					// create a new file
					IFile newLogo = imagesFolder.getFile("newLogo.png");
					FileInputStream fileStream;
					try {
						fileStream = new FileInputStream(
								"c:/MyOtherData/newLogo.png");

						newLogo.create(fileStream, false, null);
						// create closes the file stream, so no worries.
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;
	}

}
