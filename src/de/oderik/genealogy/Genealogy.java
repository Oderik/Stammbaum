package de.oderik.genealogy;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import de.oderik.genealogy.gui.MainFrame;
import de.oderik.genealogy.gui.actions.FileExitAction;

public class Genealogy {
	
	private static final Dimension MIN_SIZE = new Dimension(480, 360);
	
    private static void initGUI() {
    	try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
        JFrame.setDefaultLookAndFeelDecorated(true);

        MainFrame frame = new MainFrame();
        frame.addWindowListener(FileExitAction.getInstance());
        frame.setResizable(true);
        
        frame.pack();
        frame.setSize(Math.max(frame.getPreferredSize().width, MIN_SIZE.width), Math.max(frame.getPreferredSize().height, MIN_SIZE.height));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                initGUI();
            }
        });
	}
}
