package de.oderik.genealogy.gui.panels;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class PersonSelectionPanel extends JPanel {
	
	private JComboBox personComboBox;
	private JButton createPersonButton;
	private JButton editPersonButton;
	private JButton erasePersonButton;

	public PersonSelectionPanel() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		personComboBox = new JComboBox();
		personComboBox.setEditable(true);
		add(personComboBox);
		
		createPersonButton = new JButton("Erstellen");
		add(createPersonButton);
		
		editPersonButton = new JButton("Bearbeiten");
		add(editPersonButton);
		
		erasePersonButton = new JButton("Löschen");
		add(erasePersonButton);
	}
}
