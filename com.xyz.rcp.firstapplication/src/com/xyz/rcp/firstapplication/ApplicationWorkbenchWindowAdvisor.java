package com.xyz.rcp.firstapplication;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MenuDetectEvent;
import org.eclipse.swt.events.MenuDetectListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tray;
import org.eclipse.swt.widgets.TrayItem;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

	private IWorkbenchWindow window;
	private TrayItem trayItem;
	private Image trayImage;

	public ApplicationWorkbenchWindowAdvisor(
			IWorkbenchWindowConfigurer configurer) {
		super(configurer);
	}

	public ActionBarAdvisor createActionBarAdvisor(
			IActionBarConfigurer configurer) {
		return new ApplicationActionBarAdvisor(configurer);
	}

	public void preWindowOpen() {
		IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
		configurer.setInitialSize(new Point(400, 300));
		configurer.setShowCoolBar(false);
		configurer.setShowStatusLine(false);

		// TODO set true to display the Perspective Bar
		configurer.setShowPerspectiveBar(true);
		// configurer.setShowMenuBar(true);
	}

	// TODO add taskBar
	// As of here is the new stuff
	@Override
	public void postWindowOpen() {
		super.postWindowOpen();
		window = getWindowConfigurer().getWindow();
		trayItem = initTaskItem(window);
		// Some OS might not support tray items
		if (trayItem != null) {
			minimizeBehavior();
			// Create exit and about action on the icon
			hookPopupMenu();
		}

		// Register a part listener for a certain View / Part
		IWorkbenchPage page = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage();
		page.addPartListener(new IPartListener2() {
			@Override
			public void partVisible(IWorkbenchPartReference partRef) {
				System.out.println("Part visible: " + partRef.getId());
			}

			@Override
			public void partOpened(IWorkbenchPartReference partRef) {
				System.out.println("Part opened: " + partRef.getId());
			}

			@Override
			public void partInputChanged(IWorkbenchPartReference partRef) {
			}

			@Override
			public void partHidden(IWorkbenchPartReference partRef) {
				System.out.println("Part hidden: " + partRef.getId());
			}

			@Override
			public void partDeactivated(IWorkbenchPartReference partRef) {
				System.out.println("Part deactivated:" + partRef.getId());
			}

			@Override
			public void partClosed(IWorkbenchPartReference partRef) {
				System.out.println("Part closed:" + partRef.getId());
			}

			@Override
			public void partBroughtToTop(IWorkbenchPartReference partRef) {
				System.out.println("Part top:" + partRef.getId());
			}

			@Override
			public void partActivated(IWorkbenchPartReference partRef) {
				System.out.println("Part activated: " + partRef.getId());
			}
		});
	}

	// Add a listener to the shell

	private void minimizeBehavior() {
		window.getShell().addShellListener(new ShellAdapter() {
			// If the window is minimized hide the window
			public void shellIconified(ShellEvent e) {
				window.getShell().setVisible(false);
			}
		});
		// If user double-clicks on the tray icons the application will be
		// visible again
		trayItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Shell shell = window.getShell();
				if (!shell.isVisible()) {
					window.getShell().setMinimized(false);
					shell.setVisible(true);
				}
			}
		});
	}

	// We hook up on menu entry which allows to close the application
	private void hookPopupMenu() {
		trayItem.addMenuDetectListener(new MenuDetectListener() {

			@Override
			public void menuDetected(MenuDetectEvent e) {
				Menu menu = new Menu(window.getShell(), SWT.POP_UP);
				// Creates a new menu item that terminates the program
				MenuItem exit = new MenuItem(menu, SWT.NONE);
				exit.setText("Goodbye!");
				exit.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						window.getWorkbench().close();
					}
				});
				// We need to make the menu visible
				menu.setVisible(true);
			}
		});
	}

	// This methods create the tray item and return a reference
	private TrayItem initTaskItem(IWorkbenchWindow window) {
		final Tray tray = window.getShell().getDisplay().getSystemTray();
		TrayItem trayItem = new TrayItem(tray, SWT.NONE);
		trayImage = Activator.getImageDescriptor("/icons/alt_about.gif")
				.createImage();
		trayItem.setImage(trayImage);
		trayItem.setToolTipText("TrayItem");
		return trayItem;

	}

	// We need to clean-up after ourself
	@Override
	public void dispose() {
		if (trayImage != null) {
			trayImage.dispose();
		}
		if (trayItem != null) {
			trayItem.dispose();
		}
	}
}
