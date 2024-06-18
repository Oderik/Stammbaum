package de.oderik.genealogy.objects;

import java.io.Serializable;

public class UncertainData <E extends Serializable> implements Serializable {
	private static final long serialVersionUID = 2867768499462961756L;
	
	private E obj;
	private boolean isCertain;

	public UncertainData() {
		this(null, false);
	}
	
	public UncertainData(E obj) {
		this(obj, true);
	}
	
	public UncertainData(E obj, boolean certain) {
		set(obj);
		isCertain = certain;
	}
	
	public E val() {
		return obj;
	}
	
	public void set(E obj) {
		this.obj = obj;
	}
	
	public boolean isCertain() {
		return isCertain;
	}
	
	public void setCertain(boolean b) {
		isCertain = b;
	}
	
	public boolean isValid() {
		return (obj != null);
	}

	public boolean equals(UncertainData<E> obj) {
		return ((isCertain == obj.isCertain) && (val().equals(obj.val())));
	}

	@Override
	public String toString() {
		return (isCertain?"Certain: ":"Uncertain: ")+obj.toString();
	}
	
	
}
