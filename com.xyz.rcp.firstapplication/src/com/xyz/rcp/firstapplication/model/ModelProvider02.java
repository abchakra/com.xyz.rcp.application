package com.xyz.rcp.firstapplication.model;

import java.util.ArrayList;
import java.util.List;


public enum ModelProvider02 {
	INSTANCE;

	private List<Person> persons;
	private List<Organisation> orgs;

	private Industry industry;
	private Industry industryforview;

	private ModelProvider02() {
		industry = new Industry("Silicon Valley", getOrganisations());
		industryforview = new Industry("Silicon Valley", getOrganisationsforview());
	}

	public Industry getIndustry() {
		return industry;
	}
	
	public Industry getIndustryforview() {
		return industryforview;
	}

	public List<Person> getPersons() {

		persons = new ArrayList<Person>();
		// Image here some fancy database access to read the persons and to
		// put them into the model
		persons.add(new Person("Rainer", "Zufall", "male", true, 45,new Address("411001","Pune","India")));
		persons.add(new Person("Reiner", "Babbel", "male", true, 56,new Address("411002","Barcelone","Spain")));
		persons.add(new Person("Marie", "Dortmund", "female", false, 47,new Address("411003","LiverPool","England")));
		persons.add(new Person("Holger", "Adams", "male", true, 36,new Address("411004","LA","USA")));
		persons.add(new Person("Juliane", "Adams", "female", false, 55,new Address("411005","Mumbai","India")));

		return persons;
	}

	public List<Organisation> getOrganisations() {
		orgs = new ArrayList<Organisation>();
		// Image here some fancy database access to read the persons and to
		// put them into the model
		orgs.add(new Organisation("TIBCO", "Public", "Computer Software",
				"Palo Alto", 1997, 920000000, getPersons()));
		orgs.add(new Organisation("Progress Software Corporation", "Public",
				"Computer Software", "Bedford", 1981, 123214534, getPersons()));
		orgs.add(new Organisation("Tensilica Inc.", "Private", "Processor IP",
				"Bedford", 1997, 1020000, getPersons()));
		orgs.add(new Organisation("Yahoo", "Public", "Internet", "Sunnyvale",
				1981, 123214534, getPersons()));
		orgs.add(new Organisation("SanDisk Corporation", "Public",
				"Computer Storage", "Milpitas", 1988, 890000, getPersons()));
		return orgs;
	}
	
	public List<Organisation> getOrganisationsforview() {
		orgs = new ArrayList<Organisation>();
		// Image here some fancy database access to read the persons and to
		// put them into the model
		orgs.add(new Organisation("TIBCO", "Public", "Computer Software",
				"Palo Alto", 1997, 920000000, getPersons()));
		orgs.add(new Organisation("Progress Software Corporation", "Public",
				"Computer Software", "Bedford", 1981, 123214534, getPersons()));
		orgs.add(new Organisation("Tensilica Inc.", "Private", "Processor IP",
				"Bedford", 1997, 1020000, getPersons()));
		return orgs;
	}

}