package com.xyz.rcp.firstapplication.util;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.xyz.rcp.firstapplication.model.Industry;
import com.xyz.rcp.firstapplication.model.Organisation;

public class PersonTreeContentProvider implements ITreeContentProvider {

	private Industry model;

	@Override
	public void dispose() {

	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

		this.model = (Industry) newInput;

	}

	@Override
	public Object[] getElements(Object inputElement) {
		return model.getOrgs().toArray();
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof Organisation) {
			Organisation org = (Organisation) parentElement;
			return org.getEmployees().toArray();
		}
		return null;
	}

	@Override
	public Object getParent(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof Organisation) {
			return true;
		}
		return false;
	}
}
