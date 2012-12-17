package com.xyz.firstapplication.wizard;

import org.eclipse.jface.wizard.Wizard;

public class PrintWizard extends Wizard {
	 protected PrintWizardPageOne one;
	 protected PrintWizardPageTwo two;
	
	 public PrintWizard() {
		    super();
		    //setNeedsProgressMonitor(true);
		  }
	 @Override
	  public void addPages() {
	    one = new PrintWizardPageOne();
	    addPage(one);
	    two=new PrintWizardPageTwo();
	   addPage(two);
	   
	  }
	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		return true;
	}

}
