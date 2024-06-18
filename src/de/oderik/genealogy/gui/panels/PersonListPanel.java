package de.oderik.genealogy.gui.panels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import de.oderik.genealogy.GlobalConfig;
import de.oderik.genealogy.gui.actions.FileExitAction;
import de.oderik.genealogy.gui.dialogs.PersonPanelDialog;
import de.oderik.genealogy.objects.Person;
import de.oderik.genealogy.objects.PersonListModel;

public class PersonListPanel extends JPanel {
	private static final long serialVersionUID = -3793527529754472304L;
	
	JScrollPane	scrollPane;
	JList		list;
	JTable		table;
	JPanel		buttonPanel;
	JButton		addButton;
	JButton		editButton;
	JButton		removeButton;
	
	PersonListModel listModel;
	
	public PersonListPanel() {
		super();
		
		setName("Personen");
		initGUI();
		
		resetGUI();
	}

	public void resetGUI() {
		listModel = GlobalConfig.dataContainer.getPersons();
		list.setModel(listModel);
	}

	private void initGUI() {
		setLayout(new BorderLayout());
		
		list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		table = new JTable();
		
		scrollPane = new JScrollPane(list);
		
		buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		addButton = new JButton("Hinzufügen");
		
		editButton = new JButton("Bearbeiten");
		editButton.setEnabled(false);
		
		removeButton = new JButton("Entfernen");
		removeButton.setEnabled(false);
		
		buttonPanel.add(addButton);
		buttonPanel.add(editButton);
		buttonPanel.add(removeButton);
		
		add(scrollPane, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
		
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				selectionChanged(e);
			}
		});
		
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPerson();
			}
		});
		
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editPerson();
			}
		});
		
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removePerson();
			}
		});
	}

	private void addPerson() {
		PersonPanelDialog dialog = new PersonPanelDialog(Frame.getFrames()[0], null);
		dialog.setVisible(true);
		Person newPerson = dialog.getPerson();
		if (newPerson != null) {
			listModel.addElement(newPerson);
			listModel.fireElementAdded(listModel.indexOf(newPerson));
		}
	}

	private void editPerson() {
		int selectedIndex = list.getSelectedIndex();
		Person person = listModel.get(selectedIndex);
		PersonPanelDialog dialog = new PersonPanelDialog(Frame.getFrames()[0], person);
		dialog.setVisible(true);
		listModel.fireContentsChanged(selectedIndex);
	}

	private void removePerson() {
		int selectedIndex = list.getSelectedIndex();
		listModel.remove(selectedIndex);
		listModel.fireElementRemoved(selectedIndex);
	}

	private void selectionChanged(ListSelectionEvent e) {
		if (list.getSelectedIndex() == -1) {
			editButton.setEnabled(false);
			removeButton.setEnabled(false);
		} else {
			editButton.setEnabled(true);
			removeButton.setEnabled(true);
		}
	}

	public void addListSelectionListener(ListSelectionListener listener) {
		list.addListSelectionListener(listener);
	}

	public void removeListSelectionListener(ListSelectionListener listener) {
		list.removeListSelectionListener(listener);
	}

	public Person getSelectedPerson() {
		return listModel.get(list.getSelectedIndex());
	}
	
}
