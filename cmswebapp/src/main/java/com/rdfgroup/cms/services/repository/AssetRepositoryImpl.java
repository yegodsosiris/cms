package com.rdfgroup.cms.services.repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Repository;

import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import com.rdfgroup.cms.controller.jsp.MongoImage;
import com.rdfgroup.cms.services.domain.Asset;
import com.rdfgroup.cms.services.domain.Website;

@Repository
public class AssetRepositoryImpl extends BaseRepository implements AssetRepository {

	@Autowired
	private GridFsTemplate gridFsTemplate;
	
	@Override
	public Asset saveAsset(Asset asset) {
		return (Asset) insert(asset);
	}

	@Override
	public Asset updateAsset(Asset asset) {
		return (Asset) save(asset);
	}

	@Override
	public Asset getAsset(String id) throws Exception {
		return findById(id, Asset.class);
	}
	
	@Override
	public void deleteAsset(String id) throws Exception {
		Asset asset = getAsset(id);
		deleteFile(asset.getFileName());
		delete(id, Asset.class);
	}

	@Override
	public List<Asset> getAssetsByType(String type, Website website) {
		Criteria criteria = Criteria.where("type").is(type);
		criteria = filterByWebsite(website, criteria);
		return mongoTemplate.find(new Query(criteria), Asset.class);
	}


	@Override
	public List<Asset> getAssetsByKey(String key, Website website) {
		Criteria criteria = Criteria.where("key").is(key);
		criteria = filterByWebsite(website, criteria);
		return mongoTemplate.find(new Query(criteria), Asset.class);
	}

	@Override
	public String saveFile(InputStream inputStream, String imageName) throws Exception {
		GridFSFile store = gridFsTemplate.store(inputStream, imageName);
		return store.getId().toString();
	}

	public void deleteFile(String fileName) throws Exception {
		Criteria criteria = Criteria.where("filename").is(fileName);
		gridFsTemplate.delete(new Query(criteria));
	}
	
	@Override
	public MongoImage getImage(String filename) throws IOException {
		Criteria criteria = Criteria.where("filename").is(filename);
		GridFSDBFile findOne = gridFsTemplate.findOne(new Query(criteria));
		return new MongoImage(findOne);
	}
	
	@Override
	public void writeFile(MongoImage mi, String path) throws IOException {
		mi.writeTo(path);
	}

}
