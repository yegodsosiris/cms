package com.rdfgroup.cms.cmsclient;

import java.util.List;
import java.util.Map;

public interface CmsClientService {

    Map<String, Object> getContentById(String contentKey);

    Map<String, Object> getContentByUid(String uid, String language);

    List<Map<String, Object>> getContentList(String contentType, String languageIn);

}
