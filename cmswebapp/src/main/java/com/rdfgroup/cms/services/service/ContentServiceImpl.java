package com.rdfgroup.cms.services.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rdfgroup.cms.services.domain.Content;
import com.rdfgroup.cms.services.domain.Website;
import com.rdfgroup.cms.services.repository.ContentRepository;

@Service
public class ContentServiceImpl extends BaseServiceImpl implements ContentService {
	
	@Autowired
	ContentRepository contentRepository;

	@Override
	public List<Content> getContents(Website website) throws Exception {
		List<Content> contents = contentRepository.getContents(website);
		return getLatest(contents);
	}

	private List<Content> getLatest(List<Content> contents) {
		Map<String, Content> latest = new HashMap<String, Content>();
		for (Content content : contents) {
			String key = content.getKey();
			if (!latest.containsKey(key)) {
				latest.put(key, content);
				continue;
			}
			Content inserted = latest.get(key);
			if (content.isNewerThan(inserted)) {
				latest.put(key, content);
			}
		}
		List<Content> result = new ArrayList<Content>();
		Set<String> keySet = latest.keySet();
		for (String string : keySet) {
			result.add(latest.get(string));
		}
		Collections.sort(result, elementKeyComparator);
		return result;
	}

	private List<Content> getLatestByCountry(List<Content> contents) {
		Map<String, Content> latest = new HashMap<String, Content>();
		for (Content content : contents) {
			String key = content.getLanguage();
			if (!latest.containsKey(key)) {
				latest.put(key, content);
				continue;
			}
			Content inserted = latest.get(key);
			if (content.isNewerThan(inserted)) {
				latest.put(key, content);
			}
		}
		List<Content> result = new ArrayList<Content>();
		Set<String> keySet = latest.keySet();
		for (String string : keySet) {
			result.add(latest.get(string));
		}
		Collections.sort(result, elementKeyComparator);
		return result;
	}
	
	@Override
	public Content getContent(String id) throws Exception {
		return contentRepository.getContent(id);
	}

	@Override
	public Content saveContent(Content content) {
		return contentRepository.saveContent(content);
	}
	
	@Override
	public Content revertContent(Content content, Website website) {
		try {
			List<Content> contentsByKey = contentRepository.getContentsByKey(content.getKey(), website);
			List<Content> latest = getLatest(contentsByKey);
			Content t = latest.get(0);
			content.setVersion(t.getVersion());
			content.setId(null);
			content.increaseVersion();
			content.setLastModified(new Date());
		} catch (Exception e) {
			// There should be no error as we are saving an existing document
			e.printStackTrace();
		}
		return contentRepository.updateContent(content);
	}

	@Override
	public Content updateContent(Content content) {
		try {
			Content orig = contentRepository.getContent(content.getId());
			orig.setId(null);
			// to facilitate moving of content to new categories we must change the type.
			orig.setType(content.getType());
			contentRepository.saveContent(orig);
		} catch (Exception e) {
			// There should be no error as we are saving an existing document
			e.printStackTrace();
		}
		content.increaseVersion();
		content.setLastModified(new Date());
		return contentRepository.updateContent(content);
	}

	@Override
	public List<Content> getContentByType(String type, Website website) {
		List<Content> contentByType = contentRepository.getContentByType(type, website);
		return getLatest(contentByType);
	}

	@Override
	public List<Content> getContentsByKey(String key, Website website) {
		List<Content> result = contentRepository.getContentsByKey(key, website);
		Collections.sort(result, elementVersionComparator);
		return result;
	}

	@Override
	public void deleteContent(String id) {
		contentRepository.deleteContent(id);
	}

	@Override
	public List<Content> getContentListByKey(String key) {
		return getLatestByCountry(contentRepository.getContentListByKey(key));
	}

}
