package com.xyz.rcp.firstapplication.tableviewer.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import com.xyz.rcp.firstapplication.model.ModelProvider;
import com.xyz.rcp.firstapplication.util.AddPersonDialog;
import com.xyz.rcp.firstapplication.view.tableviewer.TableViewerView06;

public class AddPerson extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		ModelProvider persons = ModelProvider.INSTANCE;
		AddPersonDialog dialog = new AddPersonDialog(window.getShell());
		dialog.open();
		if (dialog.getPerson() != null) {
			persons.getPersons().add(dialog.getPerson());
			// Updating the display in the view
			IWorkbenchPage page = window.getActivePage();
			TableViewerView06 view = (TableViewerView06) page.findView(TableViewerView06.ID);
			view.refresh();
		}
		return null;
	}
}
