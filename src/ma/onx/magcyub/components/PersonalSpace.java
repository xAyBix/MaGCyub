package ma.onx.magcyub.components;

import java.io.File;


/**
 * Contains all the logic of the personal space		
 * 
 * 
 * @author Akram BELBEKRI
 * @since 2025-12-28 20:16
 */
public class PersonalSpace {
	private static final String PERSONAL_SPACE_PATH = "";
	private PersonalSpace () {}
	public static boolean create(String path) {
		if (path == null || path.isEmpty()) {
			path = ""; // Set to default path
		}
		
		File repo = new File(path);
		if (repo != null) {
			if (!repo.exists()) {
				repo.mkdirs();
				repo.setExecutable(true);
				// Set PERSONAL_SPACE_PATH to path and add it to environment variables
				
				return true;
			}
		}
		return false;
	}
	
	public static boolean destroy() {
		File repo = new File(PERSONAL_SPACE_PATH);
		if (repo != null) {
			if (!repo.exists()) {
				// Delete children if exist first
				repo.delete();
				return true;
			}
		}
		return false;
	}
	public static String getPersonalSpacePath() {
		return PERSONAL_SPACE_PATH;
	}
	

}
