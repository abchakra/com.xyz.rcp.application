package com.xyz.rcp.firstapplication.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class Organisation {

	protected String nameOfOrganisation;
	protected String type;
	protected String industry;
	protected String headquarters;
	protected int founded;
	protected long revenue;
	protected List<Person> employees;
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);

	public Organisation() {
		// TODO Auto-generated constructor stub
	}

	public Organisation(String nameOfOrganisation, String type,
			String industry, String headquarters, int founded, long revenue,
			List<Person> employees) {

		this.nameOfOrganisation = nameOfOrganisation;
		this.type = type;
		this.industry = industry;
		this.headquarters = headquarters;
		this.founded = founded;
		this.revenue = revenue;
		this.employees = employees;

	}

	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}

	public String getNameOfOrganisation() {
		return nameOfOrganisation;
	}

	public void setNameOfOrganisation(String nameOfOrganisation) {
		propertyChangeSupport.firePropertyChange("nameOfOrganisation",
				this.nameOfOrganisation,
				this.nameOfOrganisation = nameOfOrganisation);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		propertyChangeSupport.firePropertyChange("type", this.type,
				this.type = type);

	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		propertyChangeSupport.firePropertyChange("industry", this.industry,
				this.industry = industry);
	}

	public String getHeadquarters() {
		return headquarters;
	}

	public void setHeadquarters(String headquarters) {
		propertyChangeSupport.firePropertyChange("headquarters",
				this.headquarters, this.headquarters = headquarters);
	}

	public int getFounded() {
		return founded;
	}

	public void setFounded(int founded) {
		propertyChangeSupport.firePropertyChange("founded", this.founded,
				this.founded = founded);
	}

	public long getRevenue() {
		return revenue;
	}

	public void setRevenue(long revenue) {
		propertyChangeSupport.firePropertyChange("revenue", this.revenue,
				this.revenue = revenue);
	}

	public List<Person> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Person> employees) {
		propertyChangeSupport.firePropertyChange("employees", this.employees,
				this.employees = employees);
	}

	@Override
	public String toString() {
		return " [" + nameOfOrganisation + "]- [ " + founded + "]- [  " + type
				+ "]";
	}
}
