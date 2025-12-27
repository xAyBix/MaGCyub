package ma.onx.magcyub.ui;


import javax.swing.JFrame;

import ma.onx.magcyub.utils.Constants;

/**
 * 
 * The main frame that contains the GUI components
 * 
 * 
 * @author Akram BELBEKRI
 * @since 2025-12-27 20:56
 */
public class MainFrame extends JFrame{

	private static final long serialVersionUID = -8830760309829138731L;
	public static MainFrame mainFrame;
	
	public MainFrame () {
		config();
	}
	
	// Interface's frame configuration
	private void config () {
		setTitle(Constants.APP_NAME);
		//setIconImage(Constants.IMG);
		//setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	

}
