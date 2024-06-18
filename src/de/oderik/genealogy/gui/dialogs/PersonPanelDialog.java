package de.oderik.genealogy.gui.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import de.oderik.genealogy.gui.panels.PersonPanel;
import de.oderik.genealogy.objects.Person;

public class PersonPanelDialog extends JDialog implements ActionListener {
	private static final long serialVersionUID = -1534071171953291058L;
	
	private PersonPanel personPanel;
	private JPanel buttonPanel;
	private JButton okButton;
	private JButton cancelButton;
	
	private Person person;
	
	public PersonPanelDialog(Frame owner, Person person) {
		super(owner, "Person", true);
		this.person = person;
		initGUI();
	}
	
	private void initGUI() {
		setLayout(new BorderLayout());
		
		personPanel = new PersonPanel(person != null ? person : new Person());
		getContentPane().add(personPanel, BorderLayout.CENTER);
		
		buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		
		okButton = new JButton("OK");
		buttonPanel.add(okButton);
		okButton.addActionListener(this);
		
		cancelButton = new JButton("Abbrechen");
		buttonPanel.add(cancelButton);
		cancelButton.addActionListener(this);
		
		pack();
		setResizable(false);
		setLocationRelativeTo(getOwner());
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == okButton) {
			personPanel.updatePerson();
			person = personPanel.getPerson();
			setVisible(false);
		}
		if (e.getSource() == cancelButton) {
			setVisible(false);
		}
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
		personPanel.setPerson(person != null ? person : new Person());
	}
	
	public static void main(String[] args) {
		PersonPanelDialog personalDialog = new PersonPanelDialog(null, new Person());
		personalDialog.setVisible(true);
		System.out.println(personalDialog.getPerson().getName().val());
		personalDialog.dispose();
	}
}
