package de.oderik.genealogy.gui.panels;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTree;

import de.oderik.genealogy.objects.DescendantTreeModel;
import de.oderik.genealogy.objects.Person;

public class DescendantPanel extends JPanel {
	private static final long serialVersionUID = 796313116590042213L;
	
	private JTree tree;
	private JScrollPane scrollPane;
	
	private DescendantTreeModel treeModel;
	
	private void initGUI() {
		setLayout(new BorderLayout());
		
		tree = new JTree();
		scrollPane = new JScrollPane(tree);
		
		add(scrollPane, BorderLayout.CENTER);
	}
	

	public DescendantPanel(Person person) {
		super();
		setName("Nachkommen");
		initGUI();
		setPerson(person);
	}
	
	public void setPerson(Person person) {
		Container container = getParent();
		if (person != null) {
			treeModel = new DescendantTreeModel(person);
			tree.setModel(treeModel);
			if (container instanceof JTabbedPane) {
				JTabbedPane tabbedPane = (JTabbedPane) container;
				int index = tabbedPane.indexOfComponent(this);
				tabbedPane.setEnabledAt(index, true);
			}
		} else {
			tree.setModel(null);
			if (container instanceof JTabbedPane) {
				JTabbedPane tabbedPane = (JTabbedPane) container;
				int index = tabbedPane.indexOfComponent(this);
				tabbedPane.setEnabledAt(index, false);
			}
		}
	}

}
