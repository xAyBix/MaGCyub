// MaGCyub
// Copyright (C) 2026 Akram BELBEKRI
// Original creator of the software
// Licensed under GNU GPL v3

package ma.onx.magcyub.components;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

import ma.onx.magcyub.utils.Constants;

/**
 * Contains all the logic of the personal space	
 * 
 * @author Akram BELBEKRI
 * @since 2026-01-09 18:48
 * 
 */
public class PersonalSpace {
	private PersonalSpace() {
	}

	public static boolean create() {
		String path = Constants.PERSONAL_SPACE_PATH; // Set to default path

		File repo = new File(path);
		if (repo != null) {
			if (!repo.exists()) {
				repo.mkdirs();
				repo.setExecutable(true);
				return true;
			}
		}
		return false;
	}

	public static boolean destroy() {
		Path repo = Paths.get(Constants.PERSONAL_SPACE_PATH);
		try {
			if(Files.exists(repo)) {
				Files.walk(repo).sorted(Comparator.reverseOrder())
					.forEach(p -> {
						try {
							Files.delete(p);
						} catch (IOException e) {
							e.printStackTrace();
						}
					});
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
