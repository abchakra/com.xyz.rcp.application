package com.xyz.rcp.firstapplication.editor;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import com.xyz.rcp.firstapplication.model.Person;

public class MyPersonEditorInput implements IEditorInput {

	private final Person person;

	// private final Person person;

	public MyPersonEditorInput(Person person) {
		this.person = person;
	}

	public Person getPerson() {
		return person;
	}

	@Override
	public boolean exists() {
		return true;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	@Override
	public String getName() {
		return person.getFirstName() + " " + person.getLastName() + " "
				+ person.getId();
	}

	@Override
	public IPersistableElement getPersistable() {
		return null;
	}

	@Override
	public String getToolTipText() {
		return "Displays a person";
	}

	@Override
	public Object getAdapter(Class adapter) {
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + person.getId();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyPersonEditorInput other = (MyPersonEditorInput) obj;
		if (person.getId() != ((Person) other.getPerson()).getId())
			return false;
		return true;
	}

}