// MaGCyub
// Copyright (C) 2026 Akram BELBEKRI
// Original creator of the software
// Licensed under GNU GPL v3

package ma.onx.magcyub;


import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import ma.onx.magcyub.checks.Checks;
import ma.onx.magcyub.components.PersonalSpace;
import ma.onx.magcyub.properties.Settings;
import ma.onx.magcyub.ui.LoginFrame;
import ma.onx.magcyub.ui.MainFrame;


/**
 * Contains main method
 * 
 * @author Akram BELBEKRI
 * @since 2026-01-09 18:15
 * 
 */
public class Main {
	public static void main(String[] args) {
		// Enable anti-aliasing and improve font rendering
	    System.setProperty("awt.useSystemAAFontSettings", "on");
	    System.setProperty("swing.aatext", "true");
	    
	    // For high DPI displays
	    System.setProperty("sun.java2d.uiScale", "1.0");
	    
	    // Look and feel for better native font rendering
	    try {
	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		// Run UI
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				if (Checks.checkFirstTime() || !Checks.checkPersonalSpaceExistance()) {
					PersonalSpace.create();
					Settings.create();
					MainFrame.mainFrame = new MainFrame();
				}else {
					LoginFrame.loginFrame = new LoginFrame();
				}
			}
		});
		
	}
}