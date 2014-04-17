package com.rdfgroup.cms.controller.jsp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.mongodb.gridfs.GridFSDBFile;

public class MongoImage {

	private GridFSDBFile gFile;
	
	public void write(OutputStream stream) throws IOException {
		gFile.writeTo(stream);
	}

	public MongoImage(GridFSDBFile gFile) {
		super();
		this.gFile = gFile;
	}
	
	public InputStream getInputStream() throws IOException {
		return gFile.getInputStream();
	}
	
	public void writeTo(String filename) throws IOException {
		gFile.writeTo(filename);
	}
	

}
