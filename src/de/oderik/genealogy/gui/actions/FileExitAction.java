package de.oderik.genealogy.gui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.AbstractAction;

import de.oderik.genealogy.GlobalConfig;

public class FileExitAction extends AbstractAction implements WindowListener {
	private static final long serialVersionUID = -1474490081226184630L;
	
	private static FileExitAction singleton; 

	private FileExitAction() {
		super("Beenden");
	}
	
	public static FileExitAction getInstance() {
		if (singleton == null) {
			singleton = new FileExitAction();
		}
		return singleton;
	}

	public void actionPerformed(ActionEvent e) {
		GlobalConfig.mainFrame.dispose();
	}

	public void windowOpened(WindowEvent e) {
	}

	public void windowClosing(WindowEvent e) {
		System.out.println("Goodbye.");
		e.getWindow().dispose();
	}

	public void windowClosed(WindowEvent e) {
		
	}

	public void windowIconified(WindowEvent e) {
	}

	public void windowDeiconified(WindowEvent e) {
	}

	public void windowActivated(WindowEvent e) {
	}

	public void windowDeactivated(WindowEvent e) {
	}

}
