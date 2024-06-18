package de.oderik.genealogy.objects;

import javax.swing.event.EventListenerList;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

public class AncestorTreeModel implements TreeModel {
	protected EventListenerList listenerList = new EventListenerList();
	
	private Person root;

	public AncestorTreeModel(Person person) {
		super();
		root = person;
	}

	public Object getRoot() {
		return root;
	}

	public Object getChild(Object parent, int index) {
		if (parent instanceof Person) {
			Person person = (Person) parent;
			if (index == 0) {
				if (person.getFather().val() != null) {
					return person.getFather().val();
				} else {
					return person.getMother().val();
				}
			} else if (index == 1) {
				if (person.getFather().val() != null) {
					return person.getMother().val();
				}
			}
		}
		return null;
	}

	public int getChildCount(Object parent) {
		if (parent instanceof Person) {
			Person person = (Person) parent;
			return (person.getFather().val() != null ? 1:0) +
					(person.getMother().val() != null ? 1:0);
		}
		return 0;
	}

	public boolean isLeaf(Object node) {
		return getChildCount(node) == 0;
	}

	public void valueForPathChanged(TreePath path, Object newValue) {
		System.out.println("valueForPathChanged:\n\tpath: "+path.toString()+"\n\tnewValue: "+newValue.toString());
	}

	public int getIndexOfChild(Object parent, Object child) {
		if (parent != null && child != null &&
				parent instanceof Person && child instanceof Person) {
			Person father = ((Person) parent).getFather().val();
			Person mother = ((Person) parent).getMother().val();
			if (father == child) {
				return 0;
			}
			if (mother == child) {
				return (father == null)?0:1;
			}
		}
		return -1;
	}

	public void addTreeModelListener(TreeModelListener l) {
		listenerList.add(TreeModelListener.class, l);
	}

	public void removeTreeModelListener(TreeModelListener l) {
		listenerList.remove(TreeModelListener.class, l);
	}

}
