package de.oderik.genealogy.objects;

import javax.swing.event.EventListenerList;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

public class DescendantTreeModel implements TreeModel {
	
	protected EventListenerList listenerList = new EventListenerList();
	
	private Person root;

	public DescendantTreeModel(Person person) {
		super();
		root = person;
	}

	public Object getRoot() {
		return root;
	}

	public Object getChild(Object parent, int index) {
		if (parent instanceof Person) {
			Person person = (Person) parent;
			return person.getChildren().get(index);
		}
		return null;
	}

	public int getChildCount(Object parent) {
		if (parent instanceof Person) {
			Person person = (Person) parent;
			return person.getChildren().size();
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
			return ((Person) parent).getChildren().indexOf(child);
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
