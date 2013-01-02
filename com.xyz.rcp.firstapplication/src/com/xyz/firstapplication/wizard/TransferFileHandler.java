package com.xyz.firstapplication.wizard;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import com.xyz.rcp.firstapplication.util.TransferFileDialog;

public class TransferFileHandler extends AbstractHandler {
	

	

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
	//	ModelProvider persons = ModelProvider.INSTANCE;
		TransferFileDialog dialog = new TransferFileDialog(window.getShell());
		dialog.open();
		
		return null;
	}

	

}
