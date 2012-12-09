package com.xyz.rcp.firstapplication.util;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerDropAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;

import com.xyz.rcp.firstapplication.model.ModelProvider02;
import com.xyz.rcp.firstapplication.model.Organisation;
import com.xyz.rcp.firstapplication.model.Person;

public class DropListener extends ViewerDropAdapter {

	private final Viewer viewer;

	public DropListener(Viewer treeviewer) {
		super(treeviewer);
		this.viewer = treeviewer;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void drop(DropTargetEvent event) {
		int location = this.determineLocation(event);
		// String target = determineTarget(event).toString();
		String translatedLocation = "";
		switch (location) {
		case 1:
			translatedLocation = "Dropped before the target ";
			break;
		case 2:
			translatedLocation = "Dropped after the target ";
			break;
		case 3:
			translatedLocation = "Dropped on the target ";
			break;
		case 4:
			translatedLocation = "Dropped into nothing ";
			break;
		}
		System.out.println(translatedLocation);

		super.drop(event);
	}

	@Override
	public boolean performDrop(Object data) {
		// TODO Auto-generated method stub
		Widget selectedWidget = getCurrentEvent().item;
		for (Organisation organisation : ModelProvider02.INSTANCE
				.getIndustryforview().getOrgs()) {
			if (organisation.getNameOfOrganisation().equalsIgnoreCase(
					((Organisation) ((((TreeItem) selectedWidget)
							.getParentItem()).getData()))
							.getNameOfOrganisation()))
				organisation.getEmployees().add((Person) getSelectedObject());
		}

		viewer.setInput(ModelProvider02.INSTANCE.getIndustryforview());

		return false;
	}

	@Override
	public boolean validateDrop(Object target, int operation,
			TransferData transferType) {
		// TODO Auto-generated method stub
		return true;
	}

}
