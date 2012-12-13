package com.xyz.rcp.firstapplication.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
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
		FormToolkit toolkit = new FormToolkit(parent.getDisplay());
		ScrolledForm form = toolkit.createScrolledForm(parent);
		form.setText("Eclipse Forms API Example");

		// Lets make a layout for the first section of the screen
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		layout.marginWidth = 2;
		layout.marginHeight = 2;
		// Creating the Screen
		Section section = toolkit.createSection(parent, Section.DESCRIPTION
				| Section.TITLE_BAR | Section.TWISTIE);
		section.setExpanded(true);
		section.setText("Section 1 for demonstration"); //$NON-NLS-1$
		section.setDescription("This demonstrates the usage of section");
		// Composite for storing the data
		Composite client = toolkit.createComposite(section, SWT.WRAP);
		layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginWidth = 2;
		layout.marginHeight = 2;
		client.setLayout(layout);

		toolkit.createLabel(client, "First Name");
		Text text = toolkit.createText(client, person.getFirstName());
		text.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
		toolkit.createLabel(client, "Last Name");
		Text lastName = toolkit.createText(client, person.getLastName());
		lastName.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true,
				false));

		toolkit.paintBordersFor(client);
		section.setClient(client);

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