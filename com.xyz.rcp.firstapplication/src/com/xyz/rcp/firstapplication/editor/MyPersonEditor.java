package com.xyz.rcp.firstapplication.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import com.xyz.rcp.firstapplication.model.Address;
import com.xyz.rcp.firstapplication.model.Person;

public class MyPersonEditor extends EditorPart {
	public static final String ID = "com.xyz.rcp.firstapplication.personeditor";
	private Person person;
	private MyPersonEditorInput input;
	private boolean dirty = false;

	// Will be called before createPartControl
	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		if (!(input instanceof MyPersonEditorInput)) {
			throw new RuntimeException("Wrong input");
		}

		MyPersonEditorInput new_name = (MyPersonEditorInput) input;
		this.input = (MyPersonEditorInput) input;
		setSite(site);
		setInput(input);
		person = new Person("Tom", "Hillman", "male", true, 45, new Address(
				"411001", "Pune", "India"));
		person.setID(17818);
		// person = MyModel.getInstance().getPersonById(this.input.getId());

		setPartName("Person ID: " + person.getId());
	}

	@Override
	public void createPartControl(Composite parent) {
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		parent.setLayout(layout);
		Label label1 = new Label(parent, SWT.NONE);
		label1.setText("First Name");
		Text text = new Text(parent, SWT.BORDER);
		text.setText(person.getFirstName());
		text.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
		new Label(parent, SWT.NONE).setText("Last Name");
		Text lastName = new Text(parent, SWT.BORDER);
		lastName.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true,
				false));
		lastName.setText(person.getLastName());
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// person.getAddress().setCountry(text2.getText());
	}

	@Override
	public void doSaveAs() {
	}

	@Override
	public boolean isDirty() {
		return dirty;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	public void setFocus() {
	}

	protected void setDirty(boolean value) {
		dirty = value;
		firePropertyChange(PROP_DIRTY);
	}

}