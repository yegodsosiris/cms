package com.rdfgroup.cms.services.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.rdfgroup.cms.model.AssetUploadModel;
import com.rdfgroup.cms.services.domain.Asset;
import com.rdfgroup.cms.services.domain.Website;
import com.rdfgroup.cms.services.repository.AssetRepository;

@Service
public class AssetServiceImpl extends BaseServiceImpl implements AssetService {
	
	@Autowired
	AssetRepository assetRepository;

	@Override
	public Asset getAsset(String id) throws Exception {
		return assetRepository.getAsset(id);
	}

	@Override
	public Asset updateAsset(HttpServletRequest request, AssetUploadModel assetUploadModel) throws Exception {

		Asset asset = assetUploadModel.getAsset();
		Mapper mapper = new DozerBeanMapper();
		Asset copy = mapper.map(asset, Asset.class);
		copy.setId(null);
		copy.increaseVersion();
		CommonsMultipartFile file = assetUploadModel.getFile();
		String originalFilename = file.getOriginalFilename();
		copy.setLastModified(new Date());
		Asset savedAsset = assetRepository.saveAsset(copy);
		if (file != null && !StringUtils.isBlank(originalFilename)) {
			copy.setOriginalName(originalFilename);
			copy.initFileName();
			assetRepository.updateAsset(copy);
			try {
				assetRepository.saveFile(file.getInputStream(), copy.getFileName());
			} catch (Exception e1) {
				assetRepository.deleteAsset(savedAsset.getId());
				throw new IllegalStateException("Unable to save asset");
			}
		}
		return assetRepository.updateAsset(asset);
		
	}

	@Override
	public Asset saveAsset(AssetUploadModel assetUploadModel, Website website) throws Exception {
		Asset asset = assetUploadModel.getAsset();
		asset.setLanguage(website.getLanguage());
		CommonsMultipartFile file = assetUploadModel.getFile();
		asset.setOriginalName(file.getOriginalFilename());
		asset.setSize(file.getSize());
		asset.setLastModified(new Date());
		asset = assetRepository.saveAsset(asset);
		asset.initFileName();
		assetRepository.updateAsset(asset);
		try {
			assetRepository.saveFile(file.getInputStream(), asset.getFileName());
		} catch (Exception e1) {
			assetRepository.deleteAsset(asset.getId());
			throw new IllegalStateException("Unable to save asset");
		}
		return asset;
	}
	
	@Override
	public void deleteAsset(String id) throws Exception {
		assetRepository.deleteAsset(id);
	}

	@Override
	public List<Asset> getAssetsByType(String type, Website website) {
		return getLatest(assetRepository.getAssetsByType(type, website));
	}

	@Override
	public List<Asset> getAssetsByKey(String key, Website website) {
		List<Asset> result = assetRepository.getAssetsByKey(key, website);
		Collections.sort(result, elementVersionComparator);
		return result;
	}
	
	@Override
	public Asset revertAsset(Asset asset, Website website) {
		try {
			List<Asset> contentsByKey = assetRepository.getAssetsByKey(asset.getKey(), website);
			List<Asset> latest = getLatest(contentsByKey);
			Asset t = latest.get(0);
			asset.setVersion(t.getVersion());
			asset.setId(null);
			asset.increaseVersion();
			asset.setLastModified(new Date());
		} catch (Exception e) {
			// There should be no error as we are saving an existing document
			e.printStackTrace();
		}
		return assetRepository.updateAsset(asset);
	}
	
	private List<Asset> getLatest(List<Asset> contents) {
		Map<String, Asset> latest = new HashMap<String, Asset>();
		for (Asset content : contents) {
			String key = content.getKey();
			if (!latest.containsKey(key)) {
				latest.put(key, content);
				continue;
			}
			Asset inserted = latest.get(key);
			if (content.isNewerThan(inserted)) {
				latest.put(key, content);
			}
		}
		List<Asset> result = new ArrayList<Asset>();
		Set<String> keySet = latest.keySet();
		for (String string : keySet) {
			result.add(latest.get(string));
		}
		Collections.sort(result, elementKeyComparator);
		return result;
	}

}
