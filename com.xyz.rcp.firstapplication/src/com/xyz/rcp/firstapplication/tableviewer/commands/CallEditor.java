package com.xyz.rcp.firstapplication.tableviewer.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

import com.xyz.rcp.firstapplication.editor.MyPersonEditor;
import com.xyz.rcp.firstapplication.editor.MyPersonEditorInput;
import com.xyz.rcp.firstapplication.model.Person;
import com.xyz.rcp.firstapplication.view.treeviewer.TreeViewerView;

public class CallEditor extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		System.out.println("called");
		// Get the view
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		IWorkbenchPage page = window.getActivePage();
		TreeViewerView view = (TreeViewerView) page.findView(TreeViewerView.ID);
		// Get the selection
		ISelection selection = view.getSite().getSelectionProvider()
				.getSelection();
		if (selection != null && selection instanceof IStructuredSelection) {
			Object obj = ((IStructuredSelection) selection).getFirstElement();
			// If we had a selection lets open the editor
			if (obj != null && obj instanceof Person) {
				Person person = (Person) obj;
				MyPersonEditorInput input = new MyPersonEditorInput(person);
				try {
					page.openEditor(input, MyPersonEditor.ID);

				} catch (PartInitException e) {
					throw new RuntimeException(e);
				}
			}
		}
		return null;
	}

}