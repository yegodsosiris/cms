package com.rdfgroup.cms.model;

import java.io.Serializable;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.rdfgroup.cms.services.domain.Asset;

public class AssetUploadModel implements Serializable {
	
	private static final long serialVersionUID = 5753323191896939478L;
	
	private CommonsMultipartFile file = null;
	private Asset asset = new Asset();
	
	
	public AssetUploadModel() {
		
	}
	
	public AssetUploadModel(Asset asset) {
		this.asset=asset;
	}
	
	public AssetUploadModel(String type) {
		this.asset = new Asset(type);
	}

	public Asset getAsset() {
		return asset;
	}
	
	public void setAsset(Asset asset) {
		this.asset = asset;
	}
	
	public CommonsMultipartFile getFile() {
		return file;
	}
	
	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}

	
	
}
