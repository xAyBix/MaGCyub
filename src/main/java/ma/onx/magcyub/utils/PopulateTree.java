// MaGCyub
// Copyright (C) 2026 Akram BELBEKRI
// Original creator of the software
// Licensed under GNU GPL v3

package ma.onx.magcyub.utils;

import java.io.File;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Contains the logic of populate tree
 * 
 * @author Akram BELBEKRI
 * @since 2026-01-11 03:26
 * 
 */
public class PopulateTree {
	public static void populateTree(DefaultMutableTreeNode parent, File directory) {
	    if (directory == null || !directory.isDirectory()) {
	        return;
	    }
	    
	    File[] files = directory.listFiles();
	    if (files == null) return;
	    
	    for (File file : files) {
	        if (file.isDirectory()) {
	            DefaultMutableTreeNode node = new DefaultMutableTreeNode(file);
	            parent.add(node);
	            populateTree(node, file);
	        }
	    }
	}

}
