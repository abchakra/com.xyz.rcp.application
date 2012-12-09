package com.xyz.rcp.firstapplication.util;

import org.eclipse.jface.viewers.TableViewer;

import com.xyz.rcp.firstapplication.model.Person;

public class LastNameEditingSupport extends FirstNameEditingSupport {

	private final TableViewer viewer;

	public LastNameEditingSupport(TableViewer viewer) {
		super(viewer);
		this.viewer = viewer;
	}

	@Override
	protected Object getValue(Object element) {
		return ((Person) element).getLastName();
	}

	@Override
	protected void setValue(Object element, Object value) {
		((Person) element).setLastName(String.valueOf(value));
		viewer.update(element, null);
	}
}