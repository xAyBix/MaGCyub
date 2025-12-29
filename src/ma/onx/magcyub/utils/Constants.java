package ma.onx.magcyub.utils;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * 
 * 
 * @author Akram BELBEKRI
 * @since 2025-12-27 20:27
 */
public final class Constants {
	// A private constructor to avoid instantiation
	private Constants() {
	}

	// App properties
	public static final String APP_NAME = "MaGCyub";
	public static final Image IMG = getImage();

	// Get screen dimensions
	public static final Dimension SCREEN_DIMENSIONS = Toolkit.getDefaultToolkit().getScreenSize();
	public static final double SCREEN_HEIGHT = SCREEN_DIMENSIONS.getHeight();
	public static final double SCREEN_WIDTH = SCREEN_DIMENSIONS.getWidth();

	// Default application frame dimensions (set to 90% of screen dimensions)
	public static final int FRAME_WIDTH = (int) (SCREEN_WIDTH * 0.9);
	public static final int FRAME_HEIGHT = (int) (SCREEN_HEIGHT * 0.9);

	private static Image getImage() {
		try {
			return ImageIO.read(new File("src/resources/img/icon.png"));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
