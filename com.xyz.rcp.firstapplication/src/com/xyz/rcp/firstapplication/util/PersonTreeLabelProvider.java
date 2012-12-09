package com.xyz.rcp.firstapplication.util;

import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import com.xyz.rcp.firstapplication.model.Organisation;
import com.xyz.rcp.firstapplication.model.Person;

public class PersonTreeLabelProvider extends LabelProvider {
	private static final Image ORGANISATION = getImage("org_16.png");
	private static final Image PERSON = getImage("person_16.png");
	private static final Image PERSON_INFO = getImage("person_info_16.png");

	@Override
	public String getText(Object element) {
		if (element instanceof Organisation) {
			Organisation org = (Organisation) element;
			return org.getNameOfOrganisation() + " - " + org.getType();
		} else if (element instanceof Person) {
			Person person = (Person) element;
			return person.getFirstName() + " - " + person.getLastName();
		}
		return "";
	}

	@Override
	public Image getImage(Object element) {
		if (element instanceof Organisation) {
			return ORGANISATION;
		} else if (element instanceof Person) {
			return PERSON;
		}
		return PERSON_INFO;
	}

	// Helper Method to load the images
	private static Image getImage(String file) {
		Bundle bundle = FrameworkUtil.getBundle(PersonTreeLabelProvider.class);
		URL url = FileLocator.find(bundle, new Path("icons/" + file), null);
		ImageDescriptor image = ImageDescriptor.createFromURL(url);
		return image.createImage();

	}
}
