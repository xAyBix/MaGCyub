// MaGCyub
// Copyright (C) 2026 Akram BELBEKRI
// Original creator of the software
// Licensed under GNU GPL v3

package ma.onx.magcyub;


import javax.swing.SwingUtilities;

import ma.onx.magcyub.ui.LoginFrame;
import ma.onx.magcyub.ui.RegisterFrame;
import ma.onx.magcyub.utils.Constants;


/**
 * Contains main method
 * 
 * @author Akram BELBEKRI
 * @since 2026-01-09 18:15
 * 
 */
public class Main {
	public static void main(String[] args) {
		// Run UI
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				if (System.getenv(Constants.APP_ENV) == null) {
					RegisterFrame.registerFrame = new RegisterFrame();
				}else {
					LoginFrame.loginFrame = new LoginFrame();
				}
			}
		});
		
	}
}