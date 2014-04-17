package com.rdfgroup.cms.services.domain;

import java.text.DecimalFormat;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class Asset extends BaseCmsElement {

	private static final long serialVersionUID = -2157795068626201532L;

	private String originalName;
	private String category;
	private String fileName;

	private long size;
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public Asset() {
		
	}
	
	public Asset(String type) {
		setType(type);
	}
	public String getOriginalName() {
		return originalName;
	}
	public void setOriginalName(String name) {
		this.originalName = name;
	}

	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	private String getExtn(String originalFilename) {
		return StringUtils.substringAfter(originalFilename, ".");
	}

	public void initFileName() {
		fileName = String.format("%s.%s", getId(), getExtn(originalName));
	}

	public void setSize(long size) {
		this.size=size;
	}
	
	public long getSize() {
		return size;
	}
	
	public String getExtn() {
		if (!StringUtils.contains(originalName, '.')) {
			return "";
		}
		return StringUtils.substringAfter(originalName, ".");
	}
	
	public String getReadableFileSize() {
	    if(size <= 0) return "0";
	    final String[] units = new String[] { "B", "KB", "MB", "GB", "TB" };
	    int digitGroups = (int) (Math.log10(size)/Math.log10(1024));
	    return new DecimalFormat("#,##0.#").format(size/Math.pow(1024, digitGroups)) + " " + units[digitGroups];
	}
	
}
