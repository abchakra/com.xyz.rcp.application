package com.xyz.firstapplication.wizard;


import java.util.ArrayList;

import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.xyz.rcp.firstapplication.model.Industry;
import com.xyz.rcp.firstapplication.model.ModelProvider;
import com.xyz.rcp.firstapplication.model.Organisation;
import com.xyz.rcp.firstapplication.util.PersonTreeContentProvider;
import com.xyz.rcp.firstapplication.util.PersonTreeLabelProvider;

public class PrintWizardPageTwo extends WizardPage  {
	 public Composite containerfortree,buttonscontainer;
	 public Button print;
	 public CheckboxTreeViewer treeviewer;
     public Industry industry;
     
	protected PrintWizardPageTwo() {
		super("Organiation/emplyee");
		// TODO Auto-generated constructor stub
		setTitle("ORG/EMP");
		setDescription("Shows Organisation and employee");
	}

	@Override
	public void createControl(Composite parent) {
		// TODO Auto-generated method stub
		containerfortree=new Composite(parent,SWT.NULL);
		containerfortree.setLayout(new GridLayout(1,false));
		treeviewer = new CheckboxTreeViewer(containerfortree);
		treeviewer.getTree().setLayoutData(new GridData(GridData.FILL_BOTH));
		treeviewer.setContentProvider(new PersonTreeContentProvider());
		treeviewer.setLabelProvider(new PersonTreeLabelProvider());
		treeviewer.setInput(industry);
		
		buttonscontainer=new Composite(containerfortree, SWT.NULL);
		buttonscontainer.setLayout(new GridLayout(1,false));
		print=new Button(buttonscontainer, SWT.PUSH);
		print.setText("Print");
		print.setSize(400,200);
		    setControl(containerfortree);
		    setPageComplete(false);	
		
	}
	
	public void setInputForPageTwo(Object input[])
	{
	  ArrayList<Organisation> org=new ArrayList<Organisation>();
	  
	  for(Object obj:input)
	  {
		 org.add((Organisation) obj);
		  
	  }
	  industry=new Industry("asdasd",org);
	  treeviewer.setInput(industry);
	  treeviewer.refresh();
	  treeviewer.expandAll();
	  
	}

}
