// MaGCyub
// Copyright (C) 2026 Akram BELBEKRI
// Original creator of the software
// Licensed under GNU GPL v3

package ma.onx.magcyub.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.table.DefaultTableModel;

/**
 * Contains the logic of file table
 * 
 * @author Akram BELBEKRI
 * @since 2026-01-11 03:29
 * 
 */
public class FileTable {
	// Helper method to update file table
	public static void updateFileTable(DefaultTableModel tableModel, File directory) {
		tableModel.setRowCount(0); // Clear existing rows

		File[] files = directory.listFiles();
		if (files == null)
			return;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		for (File file : files) {
			String name = file.getName();
			String type = file.isDirectory() ? "Folder" : "File";
			String size = file.isDirectory() ? "-" : formatFileSize(file.length());
			String modified = sdf.format(new Date(file.lastModified()));

			tableModel.addRow(new Object[] { name, type, size, modified });
		}
	}

	// Helper method to format file size
	public static String formatFileSize(long size) {
		if (size < 1024)
			return size + " B";
		if (size < 1024 * 1024)
			return String.format("%.2f KB", size / 1024.0);
		if (size < 1024 * 1024 * 1024)
			return String.format("%.2f MB", size / (1024.0 * 1024));
		return String.format("%.2f GB", size / (1024.0 * 1024 * 1024));
	}
}
