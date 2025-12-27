package ma.onx.magcyub.utils;

import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * 
 * 
 * @author Akram BELBEKRI
 * @since 2025-12-27 20:27
 */
public class Constants {
	public static final String APP_NAME = "MaGCyub";
	public static final Image IMG = getImage();
	
	
	private static Image getImage () {
		try {
			return ImageIO.read(new File("src/resources/img/icon.png"));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
