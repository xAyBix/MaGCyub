package ma.onx.magcyub;

import javax.swing.SwingUtilities;

import ma.onx.magcyub.ui.MainFrame;


/**
 * 
 * Main class
 * 
 * 
 * @author Akram BELBEKRI
 * @since 2025-12-27 20:24
 */
public class MaGCyubApplication {

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
