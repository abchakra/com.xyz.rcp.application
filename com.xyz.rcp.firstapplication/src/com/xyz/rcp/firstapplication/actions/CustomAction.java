package com.xyz.rcp.firstapplication.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;

public class CustomAction extends Action implements IWorkbenchAction {

	private static final String ID = "com.xyz.rcp.firstapplication.CustomAction";

	public CustomAction() {
		setId(ID);
	}

	public void run() {

		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				.getShell();
		String dialogBoxTitle = "Message";
		String message = "You clicked something!";
		MessageDialog.openInformation(shell, dialogBoxTitle, message);

	}

	public void dispose() {
	}

}
