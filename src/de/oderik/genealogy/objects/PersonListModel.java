package de.oderik.genealogy.objects;

import java.util.Collection;
import java.util.Vector;

import javax.swing.ListModel;
import javax.swing.event.EventListenerList;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

public class PersonListModel extends Vector<Person> implements ListModel {
	private static final long serialVersionUID = 357365327077207746L;
	
	protected EventListenerList listenerList = new EventListenerList();

	public void addListDataListener(ListDataListener l) {
		listenerList.add(ListDataListener.class, l);
	}

	public void removeListDataListener(ListDataListener l) {
		listenerList.remove(ListDataListener.class, l);
	}

	public PersonListModel(int initialCapacity, int capacityIncrement) {
		super(initialCapacity, capacityIncrement);
	}

	public PersonListModel(int initialCapacity) {
		super(initialCapacity);
	}

	public PersonListModel() {
		super();
	}

	public PersonListModel(Collection<? extends Person> c) {
		super(c);
	}

	public int getSize() {
		return size();
	}

	public Object getElementAt(int index) {
		return elementAt(index);
	}

	public Vector<Person> getFilteredPersons(PersonFilter filter) {
		Vector<Person> filteredPersons = new Vector<Person>();
		for (Person person : this) {
			if (filter.checkPerson(person)) {
				filteredPersons.addElement(person);
			}
		}
		return filteredPersons;
	}
	
	public void fireContentsChanged(int index) {
		ListDataEvent evt = new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, index, index);

		ListDataListener[] listeners = listenerList.getListeners(ListDataListener.class);
		for (ListDataListener listener : listeners) {
			listener.contentsChanged(evt);
		}
	}
	
	public void fireElementAdded(int index) {
		ListDataEvent evt = new ListDataEvent(this, ListDataEvent.INTERVAL_ADDED, index, index);

		ListDataListener[] listeners = listenerList.getListeners(ListDataListener.class);
		for (ListDataListener listener : listeners) {
			listener.intervalAdded(evt);
		}
	}

	public void fireElementRemoved(int index) {
		ListDataEvent evt = new ListDataEvent(this, ListDataEvent.INTERVAL_REMOVED, index, index);

		ListDataListener[] listeners = listenerList.getListeners(ListDataListener.class);
		for (ListDataListener listener : listeners) {
			listener.intervalRemoved(evt);
		}
	}
}
