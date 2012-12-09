package com.xyz.rcp.firstapplication.util;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.TextTransfer;

import com.xyz.rcp.firstapplication.model.Person;

public class DragListener implements DragSourceListener {
	private final TreeViewer viewer;

	public DragListener(TreeViewer treeviewer)
	{
	  viewer=treeviewer;
		
	}
	
	@Override
	public void dragStart(DragSourceEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dragSetData(DragSourceEvent event) {
		// TODO Auto-generated method stub
		// Here you do the convertion to the type which is expected.
	    IStructuredSelection selection = (IStructuredSelection) viewer
	    .getSelection();
	    Person firstElement = (Person) selection.getFirstElement();
	    if (TextTransfer.getInstance().isSupportedType(event.dataType)){
	    	event.data=firstElement.getFirstName() + " - " + firstElement.getLastName();
	    }
		
	}

	@Override
	public void dragFinished(DragSourceEvent event) {
		// TODO Auto-generated method stub
		System.out.println("Finshed Drag");
	}

}
