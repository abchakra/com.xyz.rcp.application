package com.xyz.rcp.firstapplication.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Person implements PropertyChangeListener {
	private int id;
	private String firstName;
	private String lastName;
	private boolean married;
	private String gender;
	private Integer age;
	private Address address;
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);

	public Person() {
	}

	public Person(String firstName, String lastName, String gender,
			boolean married, int age, Address address) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.married = married;
		this.age = age;
		this.address = address;
	}

	public Person(String firstName, String lastName, String gender,
			boolean married, int age) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.married = married;
		this.age = age;
	}

	public Person(String firstName, String lastName, String gender,
			boolean married) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.married = married;
	}

	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}

	public String getFirstName() {
		return firstName;
	}

	public String getGender() {
		return gender;
	}

	public String getLastName() {
		return lastName;
	}

	public boolean isMarried() {
		return married;
	}

	public void setFirstName(String firstName) {
		propertyChangeSupport.firePropertyChange("firstName", this.firstName,
				this.firstName = firstName);
	}

	public void setGender(String gender) {
		propertyChangeSupport.firePropertyChange("gender", this.gender,
				this.gender = gender);
	}

	public void setLastName(String lastName) {
		propertyChangeSupport.firePropertyChange("lastName", this.lastName,
				this.lastName = lastName);
	}

	public void setMarried(boolean isMarried) {
		propertyChangeSupport.firePropertyChange("married", this.married,
				this.married = isMarried);
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		propertyChangeSupport.firePropertyChange("age", this.age,
				this.age = age);
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		address.addPropertyChangeListener("country", this);
		propertyChangeSupport.firePropertyChange("address", this.address,
				this.address = address);
	}

	@Override
	public String toString() {
		return firstName + " " + lastName;
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		propertyChangeSupport.firePropertyChange("address", null, address);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}

	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}

	public void setID(int id) {
		propertyChangeSupport.firePropertyChange("id", this.id, this.id = id);
	}

}