// MaGCyub
// Copyright (C) 2026 Akram BELBEKRI
// Original creator of the software
// Licensed under GNU GPL v3

package ma.onx.magcyub.properties;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

import com.google.gson.Gson;

import ma.onx.magcyub.checks.Checks;
import ma.onx.magcyub.io.JSONParse;
import ma.onx.magcyub.utils.Constants;

/**
 * Contains the settings
 * 
 * @author Akram BELBEKRI
 * @since 2026-01-11 20:02
 * 
 */
public class Settings {
	
	public static Settings settings;
 	public Settings () {
		if (Checks.checkSettingsFileExistance()) {
			Gson gson = new Gson();
			try {
				JSONParse.parseToSettingsObject();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			this.destroyAfter3Attempts = true;
			this.showCredentials5SecsAfterSave = false;
		}
	}
	public boolean destroyAfter3Attempts = true;
	// This could reduce security
	public boolean showCredentials5SecsAfterSave = false;
	
	public static void create () {
		File file = new File (Constants.SETTINGS_PATH);
		if (file != null) {
			if (!file.exists() || !file.canRead() || !file.canWrite()) {
				try {
					file.createNewFile();
					file.setReadable(true);
					file.setWritable(true);
					JSONParse.parseToSettingsFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
