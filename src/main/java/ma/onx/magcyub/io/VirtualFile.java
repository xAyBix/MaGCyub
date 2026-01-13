// MaGCyub
// Copyright (C) 2026 Akram BELBEKRI
// Original creator of the software
// Licensed under GNU GPL v3

package ma.onx.magcyub.io;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores virtual files
 * 
 * @author Akram BELBEKRI
 * @since 2026-01-13 21:22
 * 
 */
public class VirtualFile {
	 private static List<VirtualFile> virtualFiles = new ArrayList<>();
	 private String name;
	 private boolean isRoot;
	 private byte[] content;
	 private String virtualPath;
	 public VirtualFile () {}
	 public String getName() {
		 return name;
	 }
	 public void setName(String name) {
		 this.name = name;
	 }
	 public boolean isRoot() {
		 return isRoot;
	 }
	 public void setRoot(boolean isRoot) {
		 this.isRoot = isRoot;
	 }
	 public byte[] getContent() {
		 return content;
	 }
	 public void setContent(byte[] content) {
		 this.content = content;
	 }
	 public String getVirtualPath() {
		 return virtualPath;
	 }
	 public void setVirtualPath(String virtualPath) {
		 this.virtualPath = virtualPath;
	 }
	 
	 
	 
	 
}
