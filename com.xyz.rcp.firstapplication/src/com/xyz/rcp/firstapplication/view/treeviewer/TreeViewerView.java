package com.xyz.rcp.firstapplication.view.treeviewer;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.part.ViewPart;

import com.xyz.rcp.firstapplication.model.ModelProvider;
import com.xyz.rcp.firstapplication.model.Organisation;
import com.xyz.rcp.firstapplication.model.Person;
import com.xyz.rcp.firstapplication.util.PersonTreeContentProvider;
import com.xyz.rcp.firstapplication.util.PersonTreeLabelProvider;
import com.xyz.rcp.firstapplication.view.tableviewer.TableViewerView07;

public class TreeViewerView extends ViewPart {
	public static final String ID = "com.xyz.rcp.firstapplication.treeviewerview";
	private TreeViewer viewer;

	public void createPartControl(Composite parent) {
		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		viewer.setContentProvider(new PersonTreeContentProvider());
		viewer.setLabelProvider(new PersonTreeLabelProvider());
		// Expand the tree
		viewer.setAutoExpandLevel(2);
		// Provide the input to the ContentProvider
		viewer.setInput(ModelProvider.INSTANCE.getIndustry());

		// // Add a doubleclicklistener
		// viewer.addDoubleClickListener(new IDoubleClickListener() {
		//
		// @Override
		// public void doubleClick(DoubleClickEvent event) {
		// TreeViewer viewer = (TreeViewer) event.getViewer();
		// IStructuredSelection thisSelection = (IStructuredSelection) event
		// .getSelection();
		// Object selectedNode = thisSelection.getFirstElement();
		// viewer.setExpandedState(selectedNode,
		// !viewer.getExpandedState(selectedNode));
		// }
		// });
		//
		// viewer.getTree().addKeyListener(new KeyAdapter() {
		// @Override
		// public void keyReleased(final KeyEvent e) {
		// if (e.keyCode == SWT.DEL) {
		// final IStructuredSelection selection = (IStructuredSelection) viewer
		// .getSelection();
		// if (selection.getFirstElement() instanceof Person) {
		// Person o = (Person) selection.getFirstElement();
		// // TODO Delete the selected element from the model
		// }
		//
		// }
		// }
		// });

		viewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				if (event.getSelection() instanceof TreeSelection
						&& ((TreeSelection) event.getSelection())
								.getFirstElement() instanceof Person) {
					Person person = (Person) ((TreeSelection) event
							.getSelection()).getFirstElement();
					EmployeeDetailView part = (EmployeeDetailView) getViewSite()
							.getPage().findView(EmployeeDetailView.ID);
					part.refresh(person);
					try {
						getViewSite().getPage().showView(EmployeeDetailView.ID);
					} catch (PartInitException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if (event.getSelection() instanceof TreeSelection
						&& ((TreeSelection) event.getSelection())
								.getFirstElement() instanceof Organisation) {
					Organisation organisation = (Organisation) ((TreeSelection) event
							.getSelection()).getFirstElement();
					TableViewerView07 part = (TableViewerView07) getViewSite()
							.getPage().findView(TableViewerView07.ID);
					part.refresh(organisation);
					try {
						getViewSite().getPage().showView(TableViewerView07.ID);
					} catch (PartInitException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
		});

		// Register as Selection provider
		getSite().setSelectionProvider(viewer);
		hookDoubleClickCommand();
	}

	private void hookDoubleClickCommand() {
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				IHandlerService handlerService = (IHandlerService) getSite()
						.getService(IHandlerService.class);

				// ICommandService cmdService = (ICommandService) getSite()
				// .getService(ICommandService.class);
				// Command eatTaco = cmdService
				// .getCommand("com.xyz.rcp.firstapplication.OpenPersonEditor");
				try {
					handlerService.executeCommand(
							"com.xyz.rcp.firstapplication.openpersoneditor",
							null);
				} catch (Exception ex) {
					throw new RuntimeException(
							"com.xyz.rcp.firstapplication.openpersoneditor not found");
				}
			}
		});
	}

	public void setFocus() {
		viewer.getControl().setFocus();
	}
}