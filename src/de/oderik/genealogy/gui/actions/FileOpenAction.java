package de.oderik.genealogy.gui.actions;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JFrame;

import de.oderik.genealogy.GlobalConfig;
import de.oderik.genealogy.objects.DataContainer;

public class FileOpenAction extends AbstractAction {
	private static final long serialVersionUID = 8468652917143146170L;
	
	private static FileOpenAction singleton; 

	private FileOpenAction() {
		super("Öffnen...");
	}
	
	public static FileOpenAction getInstance() {
		if (singleton == null) {
			singleton = new FileOpenAction();
		}
		return singleton;
	}

	public void actionPerformed(ActionEvent e) {
		FileDialog fileDialog = new FileDialog(JFrame.getFrames()[0], "Öffnen", FileDialog.LOAD);
		fileDialog.setVisible(true);
		if (fileDialog.getFile() != null) {
			try {
				GlobalConfig.dataContainer = DataContainer.loadFromFile(fileDialog.getDirectory(), fileDialog.getFile());
				GlobalConfig.mainFrame.resetGUI();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
