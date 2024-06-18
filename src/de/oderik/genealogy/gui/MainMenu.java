package de.oderik.genealogy.gui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JSeparator;

import de.oderik.genealogy.gui.actions.FileExitAction;
import de.oderik.genealogy.gui.actions.FileOpenAction;
import de.oderik.genealogy.gui.actions.FileSaveAsAction;

public class MainMenu extends JMenuBar {
	private static final long serialVersionUID = 2838425299921584009L;

	JMenu fileMenu, helpMenu;

	public MainMenu() {
		super();
		initGUI();
	}

	private void initGUI() {
		fileMenu = new JMenu("Datei");
		helpMenu = new JMenu("Hilfe");
		add(fileMenu);
		add(helpMenu);
		
		fileMenu.add(FileOpenAction.getInstance());
		fileMenu.add(FileSaveAsAction.getInstance());
		fileMenu.add(new JSeparator());
		fileMenu.add(FileExitAction.getInstance());
	}
	
	
}
