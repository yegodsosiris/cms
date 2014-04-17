package com.rdfgroup.cms.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rdfgroup.cms.services.domain.Content;
import com.rdfgroup.cms.services.service.ContentService;

@Controller
@RequestMapping("/rest")
public class ContentRestController extends BaseRestController{

	@Autowired
	private ContentService contentService;

	@RequestMapping(value="/content/{key}", method = RequestMethod.GET)
	public @ResponseBody List<Content> getContentByKey(@PathVariable String key)  {
		return contentService.getContentListByKey(key);
	}
	
}
