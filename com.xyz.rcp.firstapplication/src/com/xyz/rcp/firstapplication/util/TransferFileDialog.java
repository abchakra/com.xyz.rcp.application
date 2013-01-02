package com.xyz.rcp.firstapplication.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.UIJob;

public class TransferFileDialog extends TitleAreaDialog {
	private Text sourceText;
	private Text destText;
	private Button browse1;
	private Button browse2;
	private Button buttonCopy;
	private Button buttonCancel;
	private Label srcLabel;
	private Label destLabel;

	public TransferFileDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	protected Control createContents(Composite parent) {
		Control contents = super.createContents(parent);
		setTitle("Transfer Files");
		setMessage("Please enter the src and dest file",
				IMessageProvider.INFORMATION);
		return contents;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		GridLayout layout = new GridLayout();
		layout.numColumns = 3;
		parent.setLayout(layout);
		srcLabel = new Label(parent, SWT.NONE);
		srcLabel.setText("Source File");
		sourceText = new Text(parent, SWT.BORDER);

		GridData gdText = new GridData(GridData.GRAB_HORIZONTAL
				| GridData.FILL_HORIZONTAL);
		sourceText.setLayoutData(gdText);

		browse1 = new Button(parent, SWT.PUSH);

		browse1.setText("Browse Srce");
		browse1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				browseSourceListner();
			}
		});
		destLabel = new Label(parent, SWT.NONE);
		destLabel.setText("Destination File");
		destText = new Text(parent, SWT.BORDER);
		destText.setLayoutData(gdText);

		browse2 = new Button(parent, SWT.PUSH);
		browse2.setText("Browse Dest");

		browse2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				browseDestListner();
			}

		});

		return parent;

	}

	private void browseSourceListner() {
		// TODO Auto-generated method stub
		DirectoryDialog dialog = new DirectoryDialog(getShell());
		String path = dialog.open();
		if (path != null) {
			sourceText.setText(path);
		}
	}

	private void browseDestListner() {
		// TODO Auto-generated method stub
		DirectoryDialog dialog = new DirectoryDialog(getShell());
		String path = dialog.open();
		if (path != null) {
			destText.setText(path);
		}
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		// TODO Auto-generated method stub
		((GridLayout) parent.getLayout()).numColumns++;
		Composite comp = new Composite(parent, SWT.NONE);
		GridLayout grl = new GridLayout(2, false);
		comp.setLayout(grl);
		GridData gd1 = new GridData(GridData.HORIZONTAL_ALIGN_END);
		buttonCopy = new Button(comp, SWT.PUSH);
		buttonCopy.setText("COPY");
		buttonCopy.setData(gd1);

		buttonCopy.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				String srcString = null;
				if (sourceText.getText() != null)
					srcString = sourceText.getText();
				String destString = null;
				if (destText.getText() != null)
					destString = destText.getText();
				if (!srcString.isEmpty() && !destString.isEmpty()) {
					UIJob copyJob = getJob(srcString, destString);
					
					PlatformUI.getWorkbench().getProgressService().showInDialog(Display.getCurrent().getActiveShell(), copyJob);
					copyJob.setDisplay(getShell().getDisplay());
					copyJob.setUser(true);
					copyJob.schedule();
				}
			}
		});

		GridData gd2 = new GridData(GridData.HORIZONTAL_ALIGN_END);
		buttonCancel = new Button(comp, SWT.PUSH);
		buttonCancel.setText("CANCEL");
		buttonCancel.setData(gd2);
		buttonCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				getShell().dispose();
			}
		});

	}

	protected UIJob getJob(final String srcString, final String destString) {
		return new UIJob("COPYING FILES") {
			@Override
			public IStatus runInUIThread(IProgressMonitor monitor) {
				// SubProgressMonitor copyMonitor = new SubProgressMonitor(
				// monitor, SubProgressMonitor.UNKNOWN,
				// SubProgressMonitor.PREPEND_MAIN_LABEL_TO_SUBTASK);

				SubMonitor progress = SubMonitor.convert(monitor, 100);

				try {
					// Regardless of the amount of progress reported
					// so
					// far,
					// use 0.01% of the space remaining in the
					// monitor
					// to
					// process the next node.
					progress.beginTask("Start progress", 100);

					// copyMonitor.beginTask("Copying Started", 1);
					// String srcString = null;
					// if (sourceText.getText() != null)
					// srcString = sourceText.getText();
					// String destString = null;
					// if (destText.getText() != null)
					// destString = destText.getText();

					File varFromFile = new File(srcString);
					File varToFile = new File(destString);
					copyFilesAndDir(varFromFile, varToFile,
							progress.newChild(1));
					progress.worked(100);

				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					// copyMonitor.done();
					// monitor.done();
					progress.done();
				}
				return Status.OK_STATUS;
			}
		};
	}

	/*
	 * @Override protected void createButtonsForButtonBar(Composite parent) { //
	 * TODO Auto-generated method stub ((GridLayout)
	 * parent.getLayout()).numColumns++; Composite comp=new
	 * Composite(parent,SWT.NONE); GridLayout grl=new GridLayout(2,false);
	 * comp.setLayout(grl); GridData gd1 = new
	 * GridData(GridData.HORIZONTAL_ALIGN_END); buttonCopy = new Button(comp,
	 * SWT.PUSH); buttonCopy.setText("COPY"); buttonCopy.setData(gd1);
	 * 
	 * buttonCopy.addSelectionListener(new SelectionAdapter() {
	 * 
	 * 
	 * @Override public void widgetSelected(SelectionEvent e) { // TODO
	 * Auto-generated method stub Job job = new Job("Copying Files") {
	 * 
	 * @Override protected IStatus run(IProgressMonitor monitor) { String
	 * srcString = null; if(sourceText.getText()!=null)
	 * srcString=sourceText.getText(); String destString = null;
	 * if(destText.getText()!=null) destString=destText.getText();
	 * 
	 * File varFromFile=new File(srcString); File varToFile=new
	 * File(destString); try { copyFilesAndDir(varFromFile,varToFile); } catch
	 * (IOException e) { // TODO Auto-generated catch block e.printStackTrace();
	 * }
	 * 
	 * // Use this to open a Shell in the UI thread
	 * 
	 * return Status.OK_STATUS; }
	 * 
	 * }; job.setUser(true); job.schedule(); } });
	 * 
	 * 
	 * 
	 * GridData gd2 = new GridData(GridData.HORIZONTAL_ALIGN_END); buttonCancel
	 * = new Button(comp, SWT.PUSH); buttonCancel.setText("CANCEL");
	 * buttonCancel.setData(gd2);
	 * 
	 * 
	 * }
	 */

	void copyFilesAndDir(File sourceLocation, File targetLocation,
			SubMonitor subMonitor) throws IOException {

		if (sourceLocation.isDirectory()) {
			if (!targetLocation.exists()) {
				targetLocation.mkdir();
			}
			String[] children = sourceLocation.list();

			try {
				subMonitor
						.beginTask("copyFile started now...", children.length);

				for (String string : children) {
					copyFilesAndDir(new File(sourceLocation, string), new File(
							targetLocation, string), subMonitor);
					subMonitor.worked(1);
				}
			} finally {
				subMonitor.done();
			}
		}

		else {
			FileChannel source = null;
			FileChannel destination = null;

			source = new FileInputStream(sourceLocation).getChannel();
			destination = new FileOutputStream(targetLocation).getChannel();
			if (destination != null && source != null) {
				destination.transferFrom(source, 0, source.size());
			}

			if (source != null) {
				source.close();
			}
			if (destination != null) {
				destination.close();
			}
		}
		// end of function
	}

}
