package com.rdfgroup.cms.services.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;
 
public class JsonError
{
    private final String message;
 
    public JsonError(String message) {
        this.message = message;            
    }
 
    public ModelAndView asModelAndView() {
        MappingJacksonJsonView jsonView = new MappingJacksonJsonView();
        Map<String, String> errorMap = new HashMap<String, String>();
        errorMap.put("error", message);
        return new ModelAndView(jsonView, errorMap);
    }
}
