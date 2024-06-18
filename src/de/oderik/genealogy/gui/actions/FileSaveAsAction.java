package de.oderik.genealogy.gui.actions;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;

import de.oderik.genealogy.GlobalConfig;

public class FileSaveAsAction extends AbstractAction {
	private static final long serialVersionUID = -5422341659906379778L;
	
	private static FileSaveAsAction singleton; 

	private FileSaveAsAction() {
		super("Speichern unter...");
	}
	
	public static FileSaveAsAction getInstance() {
		if (singleton == null) {
			singleton = new FileSaveAsAction();
		}
		return singleton;
	}

	public void actionPerformed(ActionEvent e) {
		FileDialog fileDialog = new FileDialog(JFrame.getFrames()[0], "Speichern", FileDialog.SAVE);
		fileDialog.setVisible(true);
		if (fileDialog.getFile() != null) {
			GlobalConfig.dataContainer.saveToFile(fileDialog.getDirectory(), fileDialog.getFile());
			System.out.println("'" + fileDialog.getDirectory() + fileDialog.getFile() + "' successfully saved.");
		}
	}
}