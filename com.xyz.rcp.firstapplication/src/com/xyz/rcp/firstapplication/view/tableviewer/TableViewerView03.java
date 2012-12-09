package com.xyz.rcp.firstapplication.view.tableviewer;

import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.window.ToolTip;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import com.xyz.rcp.firstapplication.Activator;
import com.xyz.rcp.firstapplication.model.ModelProvider;
import com.xyz.rcp.firstapplication.model.Person;
import com.xyz.rcp.firstapplication.util.FirstNameEditingSupport;

/**
 * 
 * Added menu to tableviewer and tooltip
 * 
 * @author abchakra
 * 
 */
public class TableViewerView03 extends ViewPart {
	public static final String ID = "com.xyz.rcp.firstapplication.tableviewer";

	private TableViewer viewer;
	// We use icons
	private static final Image CHECKED = Activator.getImageDescriptor(
			"icons/checked.gif").createImage();
	private static final Image UNCHECKED = Activator.getImageDescriptor(
			"icons/unchecked.gif").createImage();

	public void createPartControl(Composite parent) {
		GridLayout layout = new GridLayout(2, false);
		parent.setLayout(layout);
		Label searchLabel = new Label(parent, SWT.NONE);
		searchLabel.setText("Search: ");
		final Text searchText = new Text(parent, SWT.BORDER | SWT.SEARCH);
		searchText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL
				| GridData.HORIZONTAL_ALIGN_FILL));
		createViewer(parent);
	}

	private void createViewer(Composite parent) {
		new Label(parent, SWT.NONE);
		Composite tableComposite = new Composite(parent, SWT.NONE);
		tableComposite.setLayoutData(new GridData(GridData.FILL_BOTH
				| GridData.GRAB_HORIZONTAL));
		TableColumnLayout tableColumnLayout = new TableColumnLayout();

		viewer = new TableViewer(tableComposite, SWT.BORDER
				| SWT.FULL_SELECTION);

		// Define the menu and assign to the table
		Menu headerMenu = new Menu(parent.getShell(), SWT.POP_UP);
		viewer.getTable().setMenu(headerMenu);

		// viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
		// | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		createColumns(parent, viewer, tableColumnLayout, headerMenu);

		tableComposite.setLayout(tableColumnLayout);

		final Table table = viewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		viewer.setContentProvider(new ArrayContentProvider());
		// Get the content for the viewer, setInput will call getElements in the
		// contentProvider
		viewer.setInput(ModelProvider.INSTANCE.getPersons());

		// Make the selection available to other views
		getSite().setSelectionProvider(viewer);
		// Set the sorter for the table

		// Layout the viewer
		GridData gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		gridData.horizontalSpan = 2;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		viewer.getControl().setLayoutData(gridData);

	}

	public TableViewer getViewer() {
		return viewer;
	}

	// This will create the columns for the table
	private void createColumns(final Composite parent,
			final TableViewer viewer, TableColumnLayout tableColumnLayout,
			Menu headerMenu) {
		String[] titles = { "First name", "Last name", "Gender", "Married",
				"Age" };
		int[] bounds = { 100, 100, 100, 100, 100 };

		// First column is for the first name
		TableViewerColumn viewerNameColumn = createTableViewerColumn(titles[0],
				bounds[0], 0);
		viewerNameColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Person p = (Person) element;
				return p.getFirstName();
			}

			@Override
			public String getToolTipText(Object element) {
				Person p = (Person) element;
				return "Tooltip (" + p.getFirstName() + " " + p.getLastName()
						+ ")";
			}

			@Override
			public Point getToolTipShift(Object object) {
				return new Point(5, 5);
			}

			@Override
			public int getToolTipDisplayDelayTime(Object object) {
				return 100; // msec
			}

			@Override
			public int getToolTipTimeDisplayed(Object object) {
				return 5000; // msec
			}
		});

		viewerNameColumn.setEditingSupport(new FirstNameEditingSupport(viewer));

		// Second column is for the last name
		TableViewerColumn viewerLastNameColumn = createTableViewerColumn(
				titles[1], bounds[1], 1);
		viewerLastNameColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Person p = (Person) element;
				return p.getLastName();
			}
		});

		// Now the gender
		TableViewerColumn viewerGenderColumn = createTableViewerColumn(
				titles[2], bounds[2], 2);
		viewerGenderColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Person p = (Person) element;
				return p.getGender();
			}
		});

		// // Now the status married
		TableViewerColumn viewerMarriedColumn = createTableViewerColumn(
				titles[3], bounds[3], 3);
		viewerMarriedColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return null;
			}

			@Override
			public Image getImage(Object element) {
				if (((Person) element).isMarried()) {
					return CHECKED;
				} else {
					return UNCHECKED;
				}
			}
		});

		// Now the Age
		final TableViewerColumn viewerAgeColumn = createTableViewerColumn(
				titles[4], bounds[4], 4);

		viewerAgeColumn.setLabelProvider(new StyledCellLabelProvider() {

			@Override
			public void update(ViewerCell cell) {

				if (cell.getElement() instanceof Person) {
					Person person = (Person) cell.getElement();
					viewerAgeColumn.getColumn().getData();
					StyledString text = new StyledString();
					StyleRange myStyledRange = new StyleRange(19, 2, null,
							Display.getCurrent().getSystemColor(
									SWT.COLOR_YELLOW));
					text.append("Person age is a: ",
							StyledString.DECORATIONS_STYLER);
					text.append(" (" + person.getAge() + ") ",
							StyledString.DECORATIONS_STYLER);
					cell.setText(text.toString());

					StyleRange[] range = { myStyledRange };
					cell.setStyleRanges(range);
				}

				super.update(cell);
			}
		});

		// Fixed size
		tableColumnLayout.setColumnData(viewerNameColumn.getColumn(),
				new ColumnPixelData(100));
		// Fixed size
		tableColumnLayout.setColumnData(viewerLastNameColumn.getColumn(),
				new ColumnPixelData(100));
		// Fixed size
		tableColumnLayout.setColumnData(viewerGenderColumn.getColumn(),
				new ColumnPixelData(100));
		// Fixed size
		tableColumnLayout.setColumnData(viewerMarriedColumn.getColumn(),
				new ColumnPixelData(100));
		// Fixed size
		tableColumnLayout.setColumnData(viewerAgeColumn.getColumn(),
				new ColumnPixelData(100));

		// // Relative size
		// // Last parameter defines if the column is allowed
		// // to be resized
		// tableColumnLayout.
		// setColumnData(viewerNameColumn.getColumn(),
		// new ColumnWeightData(20, 200, true));

		// Now add a MenuItem for the colum to the table menu
		createMenuItem(headerMenu, viewerNameColumn.getColumn());
		createMenuItem(headerMenu, viewerLastNameColumn.getColumn());
		createMenuItem(headerMenu, viewerAgeColumn.getColumn());
		createMenuItem(headerMenu, viewerMarriedColumn.getColumn());
		createMenuItem(headerMenu, viewerGenderColumn.getColumn());

		// Activate the tooltip support for the viewer
		ColumnViewerToolTipSupport.enableFor(viewer, ToolTip.NO_RECREATE);

	}

	// The createMenuItem method add per column a
	// new MenuItem to the menu
	private void createMenuItem(Menu parent, final TableColumn column) {
		final MenuItem itemName = new MenuItem(parent, SWT.CHECK);
		itemName.setText(column.getText());
		itemName.setSelection(column.getResizable());
		itemName.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				if (itemName.getSelection()) {
					column.setWidth(150);
					column.setResizable(true);
				} else {
					column.setWidth(0);
					column.setResizable(false);
				}
			}
		});
	}

	private TableViewerColumn createTableViewerColumn(String title, int bound,
			final int colNumber) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(viewer,
				SWT.NONE);
		final TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);
		column.setResizable(true);
		column.setMoveable(true);
		return viewerColumn;
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */

	public void setFocus() {
		viewer.getControl().setFocus();
	}

	// IStatusLineManager manager =
	// getViewSite().getActionBars().getStatusLineManager();
	// manager.setMessage("Information for the status line");
}