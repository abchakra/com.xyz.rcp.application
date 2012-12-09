package com.xyz.rcp.firstapplication.view;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.xyz.rcp.firstapplication.model.ModelProvider02;
import com.xyz.rcp.firstapplication.util.DragListener;
import com.xyz.rcp.firstapplication.util.DropListener;
import com.xyz.rcp.firstapplication.util.PersonTreeContentProvider;
import com.xyz.rcp.firstapplication.util.PersonTreeLabelProvider;

public class IndustryDnDView extends ViewPart {
	public static final String ID = "com.xyz.rcp.firstapplication.empdetailsview";
	public TreeViewer treeViewer;

	@Override
	public void createPartControl(Composite parent) {

		treeViewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL);
		treeViewer.setContentProvider(new PersonTreeContentProvider());
		treeViewer.setLabelProvider(new PersonTreeLabelProvider());
		// Expand the tree
		treeViewer.setAutoExpandLevel(2);
		// Provide the input to the ContentProvider
		treeViewer.setInput(ModelProvider02.INSTANCE.getIndustryforview());
		int operations1 = DND.DROP_COPY | DND.DROP_MOVE;
		Transfer[] transferTypes1 = new Transfer[] { TextTransfer.getInstance() };
		treeViewer.addDragSupport(operations1, transferTypes1,
				new DragListener(treeViewer));
		int operations2 = DND.DROP_COPY | DND.DROP_MOVE;
		Transfer[] transferTypes2 = new Transfer[] { TextTransfer.getInstance() };
		treeViewer.addDropSupport(operations2, transferTypes2,
				new DropListener(treeViewer));

	}

	@Override
	public void setFocus() {
		treeViewer.getControl().setFocus();
	}

}