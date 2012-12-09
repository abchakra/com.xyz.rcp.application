package com.xyz.rcp.firstapplication;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		// set editor area false
		// layout.setEditorAreaVisible(false);
		// layout.addView(TableViewerView.ID, IPageLayout.TOP,
		// IPageLayout.RATIO_MAX, IPageLayout.ID_EDITOR_AREA);
	}
}
