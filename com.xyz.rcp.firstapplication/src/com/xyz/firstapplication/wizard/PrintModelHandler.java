package com.xyz.firstapplication.wizard;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.PlatformUI;

public class PrintModelHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		 
		//PrintWizard pr=new PrintWizard();
		 WizardDialog wizrdDialog=new WizardDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell(),new PrintWizard());
		if (wizrdDialog.open() == Window.OK) {
		      System.out.println("Ok pressed");
		    } else {
		      System.out.println("Cancel pressed");
		       }
		return null;

}

}
