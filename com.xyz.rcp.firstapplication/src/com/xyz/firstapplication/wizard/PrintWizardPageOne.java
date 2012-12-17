package com.xyz.firstapplication.wizard;

import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.xyz.rcp.firstapplication.model.Industry;
import com.xyz.rcp.firstapplication.model.ModelProvider;
import com.xyz.rcp.firstapplication.util.PersonTreeLabelProvider;
import com.xyz.rcp.firstapplication.util.PrintModelContentProvider;


public class PrintWizardPageOne extends WizardPage {

	public Composite container;
	public Label l1;
	public CheckboxTreeViewer tv;
	public Industry inds;

	protected PrintWizardPageOne() {
		super("PageOne");
		setTitle("Industry/Organisation");
		setDescription("Shows industry and its organisation");
	

	}

	@Override
	public void createControl(Composite parent) {
		// TODO Auto-generated method stub
		container = new Composite(parent, SWT.NULL);
		container.setLayout(new GridLayout(1, false));
		tv = new CheckboxTreeViewer(container);
		tv.getTree().setLayoutData(new GridData(GridData.FILL_BOTH));
		tv.setContentProvider(new PrintModelContentProvider());
		tv.setLabelProvider(new PersonTreeLabelProvider());
		tv.setInput(ModelProvider.INSTANCE.getIndustry());
		setControl(container);
		tv.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				// TODO Auto-generated method stub
				Object[] checkedItems = tv.getCheckedElements();
				System.out.println(checkedItems);

				if (getNextPage() instanceof PrintWizardPageTwo) {
					((PrintWizardPageTwo) getNextPage())
							.setInputForPageTwo(checkedItems);
				}
				//PrintWizardPageTwo.setInputForPageTwo(checkedItems);

			}
		});

		setPageComplete(true);
	}

}
