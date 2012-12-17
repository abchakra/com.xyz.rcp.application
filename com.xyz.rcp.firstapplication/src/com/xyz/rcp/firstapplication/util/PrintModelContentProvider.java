package com.xyz.rcp.firstapplication.util;

import java.util.ArrayList;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.xyz.rcp.firstapplication.model.Industry;
import com.xyz.rcp.firstapplication.model.ModelProvider;
import com.xyz.rcp.firstapplication.model.Organisation;

public class PrintModelContentProvider implements ITreeContentProvider {

	private Industry model;
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub
		this.model = (Industry) newInput;
	}

	@Override
	public Object[] getElements(Object inputElement) {
		// TODO Auto-generated method stub
		return model.getOrgs().toArray();
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		// TODO Auto-generated method stub
		if (parentElement instanceof Organisation) 
		return null;
		return null;
	}

	@Override
	public Object getParent(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		// TODO Auto-generated method stub
		return false;
	}

}
