// MaGCyub
// Copyright (C) 2026 Akram BELBEKRI
// Original creator of the software
// Licensed under GNU GPL v3

package ma.onx.magcyub.checks;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import ma.onx.magcyub.utils.Constants;

/**
 * Contains multiple checks during while running the app
 * 
 * @author Akram BELBEKRI
 * @since 2026-01-09 18:56
 * 
 */
public class Checks {
	private Checks () {}
	
	public boolean checkAll () {
		
		if (!checkPersonalSpaceExistance()) {
			return false;
		}
		
		return true;
	}
	
	public static boolean checkSettingsFileExistance () {
		return Files.exists(Paths.get(Constants.SETTINGS_PATH));
	}
	
	public static boolean checkCredentials (final String rootFile, final char[] password) {
		if (rootFile.equals("root")) {
			char[] p = "root".toCharArray();
			if (p.length == password.length) {
				for (int i = 0; i<password.length; i++) {
					if (p[i] != password[i]) {
						return false;
					}
				}
			}
			return true;
		}
		return false;
	}
	
	public static boolean checkPersonalSpaceExistance () {
		File repo = new File(Constants.PERSONAL_SPACE_PATH);
		if (repo != null) {
			if (repo.exists() && repo.isDirectory() && repo.canExecute()) {
				return true;
			}
		}
		return false;
	}
	public static boolean checkFirstTime () {
		File repo = new File(Constants.APP_FOLDER);
		if (repo != null) {
			if (repo.exists() && repo.isDirectory() && repo.canExecute()) {
				return false;
			}
		}
		return true;
	}

}