package com.rdfgroup.cms.services.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.rdfgroup.cms.model.AssetUploadModel;
import com.rdfgroup.cms.services.domain.Asset;
import com.rdfgroup.cms.services.domain.Website;


public interface AssetService {
	
	public Asset saveAsset(AssetUploadModel assetUploadModel, Website website) throws Exception;

	public Asset updateAsset(HttpServletRequest request, AssetUploadModel assetUploadModel) throws Exception;

	public void deleteAsset(String id) throws Exception;

	public List<Asset> getAssetsByType(String type, Website website);

	public Asset getAsset(String id) throws Exception;

	public List<Asset> getAssetsByKey(String key, Website website);

	public Asset revertAsset(Asset asset, Website website);

}
