// MaGCyub
// Copyright (C) 2026 Akram BELBEKRI
// Original creator of the software
// Licensed under GNU GPL v3

package ma.onx.magcyub.io;

import java.io.FileReader;
import java.io.FileWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ma.onx.magcyub.properties.Settings;
import ma.onx.magcyub.utils.Constants;

/**
 * Contains methods that convert to JSON or from JSON
 * 
 * @author Akram BELBEKRI
 * @since 2026-01-12 12:47
 * 
 */
public class JSONParse {
	
	public static void parseToSettingsFile () {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try(FileWriter fw = new FileWriter(Constants.SETTINGS_PATH)) {
			gson.toJson(Settings.settings, fw);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void parseToSettingsObject () {
		Gson gson = new Gson();
		try(FileReader fr = new FileReader(Constants.SETTINGS_PATH)) {
			Settings.settings = gson.fromJson(fr, Settings.class);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String parseFileFragment () {
		return null;
	}
	
	public static String extractFileFragment () {
		return null;
	}
  
}
