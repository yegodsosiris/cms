package com.rdfgroup.cms.cmsclient;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.rdfgroup.cms.services.domain.Website;
import com.rdfgroup.cms.util.JacksonHelper;
import com.rdfgroup.cms.util.URLConnectorHelper;
import com.rdfgroup.cms.velocity.directive.helper.DotCMSLanguageHelper;


public class DotCmsClientService implements CmsClientService {

    private static final String JSON_LIST_NAME = "contentlets";
    private static final String conhost = "+(conhost:48190c8c-42c4-46af-8d1a-0cd5db894797%20conhost:SYSTEM_HOST)";

    private final String restUri;
    
    @Autowired
    public DotCmsClientService(@Value("${cms.host}") String cmsHost) {
        this.restUri = cmsHost+"/api/content/render/false/type/json/";
    }

    private String getContentFromCmsById(String id) throws Exception {
        String query = "id/" + id;
        String desiredUrl = restUri + query;
        return URLConnectorHelper.executeGet(desiredUrl);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> getContentById(String contentKey) {
        Map<String, Object> hm;
        try {
            hm = JacksonHelper.getObjectFromJson(getContentFromCmsById(contentKey), HashMap.class);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Unable to get content (" + contentKey + ") from the CMS");
        }

        List<Map<String, Object>> value = (List<Map<String, Object>>) hm.get(JSON_LIST_NAME);
        if (value == null || value.isEmpty()) {
            return new LinkedHashMap<String, Object>();
        }
        return value.get(0);
    }

    private String getLanguage(String language) {
        if (blank(language)) {
            return "+languageId:1";
        } else {
            String id = DotCMSLanguageHelper.getLanguageIdFromCode(language);
            if (id == null) {
                throw new IllegalStateException("unrecognised language code " + language);
            }
            return String.format("+languageId:%s", id);
        }
    }

    private boolean blank(String key) {
        return StringUtils.isBlank(key);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Map<String, Object>> getContentList(String contentType, String languageIn) {
        String language = "+languageId:1";
        if (!StringUtils.isBlank(languageIn)) {
            language = getLanguage(languageIn);
        }
        String query = "query/+structureName:" + contentType + "%20+(conhost:48190c8c-42c4-46af-8d1a-0cd5db894797%20conhost:SYSTEM_HOST)%20" + language
                + "%20+deleted:false%20+working:true/orderby/modDate%20desc";
        String desiredUrl = restUri + query;
        String feed;
        try {
            feed = URLConnectorHelper.executeGet(desiredUrl);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Unable to get content (" + contentType + ") from the CMS", e);
        }
        HashMap<String, Object> hm = JacksonHelper.getObjectFromJson(feed, HashMap.class);
        List<Map<String, Object>> contenletList = (List<Map<String, Object>>) hm.get(JSON_LIST_NAME);
        return contenletList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> getContentByUid(String uid, String language) {

        StringBuilder query = new StringBuilder(restUri);
        List<String> params = new LinkedList<String>();

        query.append("query/");
        params.add(conhost);
        params.add("+deleted:false");
        params.add(String.format("+webPageContent.uid:%s", uid));
        addLanguage(language, params);
        query.append(StringUtils.join(params, "%20"));
        String feed;
        try {
            feed = URLConnectorHelper.executeGet(query.toString());
        } catch (Exception e) {
            throw new ResourceNotFoundException("Unable to get content (" + uid + ") from the CMS");
        }
        HashMap<String, Object> hm = JacksonHelper.getObjectFromJson(feed, HashMap.class);

        List<Map<String, Object>> value = (List<Map<String, Object>>) hm.get(JSON_LIST_NAME);
        if (value == null || value.isEmpty()) {
            throw new ResourceNotFoundException("Content could not be found (uid=" + uid + ", language=" + language + ") from the cms");
        }
        Map<String, Map<String, Object>> map = new HashMap<String, Map<String,Object>>();
        for (Map<String, Object> linkedHashMap : value) {
            Integer languageId = (Integer) linkedHashMap.get("languageId");
            map.put(DotCMSLanguageHelper.getCodeFromLanguageId(String.valueOf(languageId)), linkedHashMap);
        }
        Map<String, Object> linkedHashMap = map.get(language);
        if (linkedHashMap == null) {
            linkedHashMap = map.get(Website.ENGLISH);
        }
        if (linkedHashMap==null) {
            throw new ResourceNotFoundException("Content could not be found (uid=" + uid + ", language=" + language + ") from the cms");
        }
        return linkedHashMap;
    }

    private void addLanguage(String language, List<String> params) {
        params.add("+languageId:*");
    }
}
