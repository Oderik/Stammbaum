package de.oderik.genealogy.objects;

import java.io.Serializable;
import java.util.Enumeration;

public abstract class Partnership implements Serializable{
	public abstract int getPartnerCount();
	public abstract Enumeration<UncertainData<Person>> Partners();
}
