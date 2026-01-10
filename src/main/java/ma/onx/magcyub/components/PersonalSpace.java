// MaGCyub
// Copyright (C) 2026 Akram BELBEKRI
// Original creator of the software
// Licensed under GNU GPL v3

package ma.onx.magcyub.components;

import java.io.File;

import ma.onx.magcyub.utils.Constants;
import ma.onx.magcyub.utils.EnvironmentVariable;

/**
 * Contains all the logic of the personal space	
 * 
 * @author Akram BELBEKRI
 * @since 2026-01-09 18:48
 * 
 */
public class PersonalSpace {
	private static String PERSONAL_SPACE_PATH = System.getenv(Constants.APP_ENV);

	private PersonalSpace() {
	}

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
				PERSONAL_SPACE_PATH = "PATH:" + repo.getAbsoluteFile();
				EnvironmentVariable.setEnvironmentVariable(Constants.APP_ENV, PERSONAL_SPACE_PATH);
				return true;
			}
		}
		return false;
	}

	public static boolean destroy() {
		File repo = new File(PERSONAL_SPACE_PATH.replaceFirst("PATH:", ""));
		if (repo != null) {
			if (!repo.exists()) {
				EnvironmentVariable.setEnvironmentVariable(Constants.APP_ENV, "");
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
