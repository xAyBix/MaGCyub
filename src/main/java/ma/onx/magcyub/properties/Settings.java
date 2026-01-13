// MaGCyub
// Copyright (C) 2026 Akram BELBEKRI
// Original creator of the software
// Licensed under GNU GPL v3

package ma.onx.magcyub.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import ma.onx.magcyub.utils.Constants;

/**
 * Contains the settings
 * 
 * @author Akram BELBEKRI
 * @since 2026-01-11 20:02
 * 
 */
public class Settings implements Serializable {

    private static final long serialVersionUID = 1L;
    public static Settings settings;
    
    private boolean destroyAfter3Attempts = true;
    private boolean showCredentials5SecsAfterSave = false;

    private Settings() {
        // Private constructor with default values
        this.destroyAfter3Attempts = true;
        this.showCredentials5SecsAfterSave = false;
    }
    
    
    public boolean isDestroyAfter3Attempts() {
		return destroyAfter3Attempts;
	}

	public void setDestroyAfter3Attempts(boolean destroyAfter3Attempts) {
		this.destroyAfter3Attempts = destroyAfter3Attempts;
	}

	public boolean isShowCredentials5SecsAfterSave() {
		return showCredentials5SecsAfterSave;
	}

	public void setShowCredentials5SecsAfterSave(boolean showCredentials5SecsAfterSave) {
		this.showCredentials5SecsAfterSave = showCredentials5SecsAfterSave;
	}



	public static void initialize() {
        if (settings == null) {
            File file = new File(Constants.SETTINGS_PATH);
            
            if (file.exists() && file.canRead()) {
                load();
            } else {
                settings = new Settings();
                create();
            }
        }
    }

    public static void create() {
        File file = new File(Constants.SETTINGS_PATH);
        
        // Create parent directories if needed
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
        
        if (!file.exists()) {
            try {
                file.createNewFile();
                save();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void save() {
        if (settings == null) {
            System.err.println("Error: Settings is null, cannot save!");
            return;
        }
        
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(Constants.SETTINGS_PATH))) {
            oos.writeObject(settings);
            System.out.println("Settings saved successfully!");
        } catch (IOException e) {
            System.err.println("Error saving settings:");
            e.printStackTrace();
        }
    }

    public static void load() {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(Constants.SETTINGS_PATH))) {
            settings = (Settings) ois.readObject();
            System.out.println("Settings loaded successfully!");
            System.out.println(settings.destroyAfter3Attempts + " " + settings.showCredentials5SecsAfterSave);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading settings, creating new instance:");
            e.printStackTrace();
            settings = new Settings();
        }
    }
}