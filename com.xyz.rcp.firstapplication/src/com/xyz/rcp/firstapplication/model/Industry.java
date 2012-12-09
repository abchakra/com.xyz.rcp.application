package com.xyz.rcp.firstapplication.model;

import java.beans.PropertyChangeSupport;
import java.util.List;

public class Industry {
	protected String name;
	protected List<Organisation> orgs;
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);

	public Industry() {
		// TODO Auto-generated constructor stub
	}

	public Industry(String name, List<Organisation> orgs) {
		this.name = name;
		this.orgs = orgs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		propertyChangeSupport.firePropertyChange("name", this.name,
				this.name = name);
		this.name = name;
	}

	public List<Organisation> getOrgs() {
		return orgs;
	}

	public void setOrgs(List<Organisation> orgs) {
		propertyChangeSupport.firePropertyChange("orgs", this.orgs,
				this.orgs = orgs);
	}

}
