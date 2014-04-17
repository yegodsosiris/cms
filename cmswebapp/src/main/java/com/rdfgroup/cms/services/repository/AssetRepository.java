package com.rdfgroup.cms.services.repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.rdfgroup.cms.controller.jsp.MongoImage;
import com.rdfgroup.cms.services.domain.Asset;
import com.rdfgroup.cms.services.domain.Website;

public interface AssetRepository {

	public Asset saveAsset(Asset asset);

	public Asset updateAsset(Asset asset);

	public void deleteAsset (String id) throws Exception;

	public List<Asset> getAssetsByType(String type, Website website);

	public Asset getAsset(String id) throws Exception;

	public List<Asset> getAssetsByKey(String key, Website website);
	
	public String saveFile(InputStream inputStream, String imageName) throws Exception;
	
	public MongoImage getImage(String filename) throws IOException;

	public void writeFile(MongoImage mi, String path) throws IOException;

}
