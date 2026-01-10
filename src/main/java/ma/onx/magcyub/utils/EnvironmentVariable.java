// MaGCyub
// Copyright (C) 2026 Akram BELBEKRI
// Original creator of the software
// Licensed under GNU GPL v3

package ma.onx.magcyub.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * Contains the logic to set environment variables
 * 
 * @author Akram BELBEKRI
 * @since 2026-01-10 02:30
 * 
 */
public final class EnvironmentVariable {
	private EnvironmentVariable() {}
	
	public static void setEnvironmentVariable (String name, String value) {
		try {
            // Method 1: Set for current JVM process (runtime only)
            System.setProperty(name, value);
            
            // Method 2: Attempt to set system environment variable (OS-specific)
            String os = System.getProperty("os.name").toLowerCase();
            
            if (os.contains("win")) {
                // Windows: Use setx command for permanent, set for current session
                setWindowsEnvVar(name, value);
            } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
                // Unix/Linux/Mac: Modify shell profile
                setUnixEnvVar(name, value);
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	private static void setWindowsEnvVar(String name, String value) {
        try {
            // Use setx for persistent environment variable (requires admin for system-wide)
            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "setx", name, value);
            Process process = pb.start();
            int exitCode = process.waitFor();
            
            if (exitCode == 0) {
                System.out.println("✓ Windows: Variable set permanently (user level)");
                System.out.println("  Note: Restart applications to see the change");
            } else {
            	System.err.println("✗ Windows: setx command failed (exit code: " + exitCode + ")");
            }
        } catch (Exception e) {
        	System.err.println("✗ Windows: Could not execute setx - " + e.getMessage());
        }
    }
    
    private static void setUnixEnvVar(String name, String value) {
        try {
            String home = System.getProperty("user.home");
            String profilePath = home + "/.bashrc";  // or .bash_profile, .zshrc, etc.
            
            String exportLine = "export " + name + "=\"" + value + "\"";
            
            // Append to .bashrc
            try (FileWriter fw = new FileWriter(profilePath, true);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw)) {
                out.println(exportLine);
                System.out.println("✓ Unix/Linux: Added to " + profilePath);
                System.out.println("  Run 'source " + profilePath + "' or restart terminal");
            }
            
        } catch (Exception e) {
        	System.err.println("✗ Unix/Linux: Could not modify profile - " + e.getMessage());
        }
    }
}
