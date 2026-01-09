// MaGCyub
// Copyright (C) 2026 Akram BELBEKRI
// Original creator of the software
// Licensed under GNU GPL v3

package ma.onx.magcyub;

import java.io.File;

import javax.swing.SwingUtilities;

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
		// Run UI
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				MainFrame.mainFrame = new MainFrame();
			}
		});
		
	}
}