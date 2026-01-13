// MaGCyub
// Copyright (C) 2026 Akram BELBEKRI
// Original creator of the software
// Licensed under GNU GPL v3

package ma.onx.magcyub.io;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import ma.onx.magcyub.utils.Constants;

/**
 * A file segment used to store segments of files
 * 
 * @author Akram BELBEKRI
 * @since 2026-01-13 17:51
 * 
 */
public class FileSegment {
	private static List<FileSegment> fileSements = new  ArrayList<>();
	
	private String id;
	private String originalFileName;
	private boolean isEOF;
	private byte[] content;
	private String virtualPath;
	private FileSegment nextFileSegment;
	
	public FileSegment (String originalFileName, boolean isEOF, byte[] content, String virtualPath) {
		this.originalFileName = originalFileName;
		this.id = generateId();
		this.isEOF = isEOF;
		this.content = content;
		this.virtualPath = virtualPath;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public boolean isEOF() {
		return isEOF;
	}

	public void setEOF(boolean isEOF) {
		this.isEOF = isEOF;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public FileSegment getNextFileSegment() {
		return nextFileSegment;
	}

	public String getVirtualPath() {
		return virtualPath;
	}

	public void setVirtualPath(String virtualPath) {
		this.virtualPath = virtualPath;
	}
	
	

	public static List<FileSegment> getAll() {
		return fileSements;
	}
	
	private String generateId () {
		StringBuilder generatedId = new StringBuilder();
		do {
			int randomLength = ThreadLocalRandom.current().nextInt(4, 13);
			for (int i = 0; i < randomLength; i++) {
				String randomChar = Constants.CHARACTERS[ThreadLocalRandom.current().nextInt(64)];
				generatedId.append(randomChar);
			}
			
		}while (getFileSegmentById(generatedId.toString()) != null);
		
		return generatedId.toString();
	}
	
	private static FileSegment getFileSegmentById(String id) {
		for (FileSegment fs : fileSements) {
			if (fs.id.equals(id))
				return fs;
		}
		return null;
	}

}
