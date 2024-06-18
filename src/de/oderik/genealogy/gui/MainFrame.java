package de.oderik.genealogy.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import de.oderik.genealogy.GlobalConfig;
import de.oderik.genealogy.gui.panels.AncestorPanel;
import de.oderik.genealogy.gui.panels.DescendantPanel;
import de.oderik.genealogy.gui.panels.PersonListPanel;
import de.oderik.genealogy.gui.panels.PersonSelectionPanel;
import de.oderik.genealogy.objects.Person;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = -1503498498229100672L;

	private MainMenu mainMenu;
	private JTabbedPane tabbedPane;
	private PersonSelectionPanel personSelectionPanel;
	private PersonListPanel personListPanel;
	private DescendantPanel descendantPanel;
	private AncestorPanel ancestorPanel;

	public MainFrame() {
		super("Stammbaum");
		GlobalConfig.mainFrame = this;
		initGUI();
	}
	
	private void initGUI() {
		mainMenu = new MainMenu();
		setJMenuBar(mainMenu);
		
		setLayout(new BorderLayout());
		
		tabbedPane = new JTabbedPane();
		add(tabbedPane, BorderLayout.CENTER);
		
		personSelectionPanel = new PersonSelectionPanel();
		add(personSelectionPanel, BorderLayout.NORTH);
		
		personListPanel = new PersonListPanel();
		tabbedPane.add(personListPanel);
		
		descendantPanel = new DescendantPanel(null);
		tabbedPane.add(descendantPanel);
		tabbedPane.setEnabledAt(tabbedPane.indexOfComponent(descendantPanel), false);
		
		ancestorPanel = new AncestorPanel(null);
		tabbedPane.add(ancestorPanel);
		tabbedPane.setEnabledAt(tabbedPane.indexOfComponent(ancestorPanel), false);
		
		personListPanel.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				Person person = personListPanel.getSelectedPerson();
				descendantPanel.setPerson(person);
				ancestorPanel.setPerson(person);
			}
		});
	}
	
	public void resetGUI() {
		personListPanel.resetGUI();
	}
}
