package de.oderik.genealogy.objects;

import java.io.Serializable;
import java.util.Date;
import java.util.Vector;

public class Person implements Serializable {
	private static final long serialVersionUID = -2533004938232269557L;
	
	private UncertainData<Person> father, mother;
	private UncertainData<Date> dob, dod;
	private UncertainData<String> name;
	private Vector<Person> children;
	private UncertainData<Boolean> isMale;

	public Person() {
		super();
		father = new UncertainData<Person>();
		mother = new UncertainData<Person>();
		dob = new UncertainData<Date>();
		dod = new UncertainData<Date>();
		name = new UncertainData<String>();
		children = new Vector<Person>();
		isMale = new UncertainData<Boolean>(new Boolean(true), false);
	}
	
	public UncertainData<Date> getDateOfBirth() {
		return dob;
	}

	public void setDateOfBirth(UncertainData<Date> dob) {
		this.dob = dob;
	}

	public UncertainData<Date> getDateOfDeath() {
		return dod;
	}

	public void setDateOfDeath(UncertainData<Date> dod) {
		this.dod = dod;
	}

	public UncertainData<Person> getFather() {
		return father;
	}

	public void setFather(UncertainData<Person> father) {
		if (father.val() == this) {
			throw new UnsupportedOperationException("person must not be it's own father");
		}
		
		if (this.father != father) {
			if (this.father.val() != null) {
				this.father.val().removeChild(this);
			}
			this.father = father;
			if (this.father.val() != null) {
				this.father.val().addChild(this);
			}
		}
	}

	private void removeChild(Person person) {
		children.removeElement(person);
	}

	private void addChild(Person child) {
		children.addElement(child);
	}
	
	public UncertainData<Person> getMother() {
		return mother;
	}

	public void setMother(UncertainData<Person> mother) {
		if (mother.val() == this) {
			throw new UnsupportedOperationException("person must not be it's own mother");
		}
		
		if (this.mother != mother) {
			if (this.mother.val() != null) {
				this.mother.val().removeChild(this);
			}
			this.mother = mother;
			if (this.mother.val() != null)
				this.mother.val().addChild(this);
		}
	}

	public UncertainData<String> getName() {
		return name;
	}

	public void setName(UncertainData<String> name) {
		this.name = name;
	}
	
	public UncertainData<Boolean> isMale() {
		return this.isMale;
	}
	
	public void setMale(UncertainData<Boolean> isMale) {
		this.isMale = isMale;
	}
	
	public String toString() {
		String str = "";
		str += name.val();
		return str;
	}
	
	protected Vector<Person> getChildren() {
		return children;
	}
}