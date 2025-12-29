package ma.onx.magcyub.checks;

import java.io.File;

import ma.onx.magcyub.components.PersonalSpace;

/**
 * 
 * Contains multiple checks during while running the app		
 * 
 * 
 * @author Akram BELBEKRI
 * @since 2025-12-28 20:04
 */
public class Checks {
	private Checks () {}
	
	public boolean checkAll () {
		
		if (!checkPersonalSpaceExistance()) {
			return false;
		}
		
		return true;
	}
	
	public boolean checkPersonalSpaceExistance () {
		File repo = new File(PersonalSpace.getPersonalSpacePath());
		if (repo != null) {
			if (repo.exists() && repo.isDirectory() && repo.canExecute()) {
				return true;
			}
		}
		return false;
	}

}
