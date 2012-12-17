package com.xyz.rcp.firstapplication.view.tableviewer;

import java.util.ArrayList;
import java.util.List;

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
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
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
import com.xyz.rcp.firstapplication.actions.CustomAction;
import com.xyz.rcp.firstapplication.model.Organisation;
import com.xyz.rcp.firstapplication.model.Person;
import com.xyz.rcp.firstapplication.util.FirstNameEditingSupport;
import com.xyz.rcp.firstapplication.util.GenderEditingSupport;
import com.xyz.rcp.firstapplication.util.LastNameEditingSupport;
import com.xyz.rcp.firstapplication.util.MarriedEditingSupport;
import com.xyz.rcp.firstapplication.util.MyViewerComparator;
import com.xyz.rcp.firstapplication.util.PersonFilter;
import com.xyz.rcp.firstapplication.util.SearchUtil;

/**
 * Added PersonFilter to tableViewer
 * 
 * @author abchakra
 * 
 */
public class TableViewerView07 extends ViewPart {
	public static final String ID = "com.xyz.rcp.firstapplication.tableviewer";

	private TableViewer viewer;
	// We use icons
	private static final Image CHECKED = Activator.getImageDescriptor(
			"icons/checked.gif").createImage();
	private static final Image UNCHECKED = Activator.getImageDescriptor(
			"icons/unchecked.gif").createImage();
	private MyViewerComparator comparator;
	private PersonFilter filter;

	private Text searchText;
	private static Color colorYellow = Display.getCurrent().getSystemColor(
			SWT.COLOR_YELLOW);

	public void createPartControl(Composite parent) {
		GridLayout layout = new GridLayout(2, false);
		parent.setLayout(layout);
		Label searchLabel = new Label(parent, SWT.NONE);
		searchLabel.setText("Search: ");
		searchText = new Text(parent, SWT.BORDER | SWT.SEARCH);
		searchText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL
				| GridData.HORIZONTAL_ALIGN_FILL));
		createViewer(parent);

		// Set the sorter for the table
		comparator = new MyViewerComparator();
		viewer.setComparator(comparator);

		filter = new PersonFilter();
		// New to support the search
		searchText.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke) {
				filter.setSearchText(searchText.getText());
				viewer.refresh();
			}

		});

		viewer.addFilter(filter);

		// Custom Action for the View's Menu
		CustomAction lCustomAction = new CustomAction();
		lCustomAction.setText("Open Dialog Box");
		lCustomAction.setImageDescriptor(Activator
				.getImageDescriptor("icons/custom.png"));
		getViewSite().getActionBars().getToolBarManager().add(lCustomAction);
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

		viewerNameColumn.setLabelProvider(new StyledCellLabelProvider() {
			@Override
			public void update(ViewerCell cell) {
				String search = searchText.getText();
				Person person = (Person) cell.getElement();
				String cellText = person.getFirstName();
				cell.setText(cellText);
				if (search != null && search.length() > 0) {
					int intRangesCorrectSize[] = SearchUtil
							.getSearchTermOccurrences(search, cellText);
					List<StyleRange> styleRange = new ArrayList<StyleRange>();
					for (int i = 0; i < intRangesCorrectSize.length / 2; i++) {
						int start = intRangesCorrectSize[i];
						int length = intRangesCorrectSize[++i];
						StyleRange myStyledRange = new StyleRange(start,
								length, null, colorYellow);

						styleRange.add(myStyledRange);
					}
					cell.setStyleRanges(styleRange
							.toArray(new StyleRange[styleRange.size()]));
				} else {
					cell.setStyleRanges(null);
				}

				super.update(cell);

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

		// add EditingSupport for each column
		viewerNameColumn.setEditingSupport(new FirstNameEditingSupport(viewer));
		viewerLastNameColumn.setEditingSupport(new LastNameEditingSupport(
				viewer));
		viewerMarriedColumn
				.setEditingSupport(new MarriedEditingSupport(viewer));
		viewerGenderColumn.setEditingSupport(new GenderEditingSupport(viewer));

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

		// add comparator for each column
		column.addSelectionListener(getSelectionAdapter(column, colNumber));

		return viewerColumn;
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */

	public void setFocus() {
		viewer.getControl().setFocus();
	}

	private SelectionAdapter getSelectionAdapter(final TableColumn column,
			final int index) {
		SelectionAdapter selectionAdapter = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				comparator.setColumn(index);
				int dir = comparator.getDirection();
				viewer.getTable().setSortDirection(dir);
				viewer.getTable().setSortColumn(column);
				viewer.refresh();
			}
		};
		return selectionAdapter;
	}

	// IStatusLineManager manager =
	// getViewSite().getActionBars().getStatusLineManager();
	// manager.setMessage("Information for the status line");

	// Used to update the viewer from outsite
	public void refresh(Organisation organisation) {
		// Get the content for the viewer, setInput will call getElements in the
		// contentProvider

		viewer.setInput(organisation.getEmployees());
		viewer.refresh();
	}
}