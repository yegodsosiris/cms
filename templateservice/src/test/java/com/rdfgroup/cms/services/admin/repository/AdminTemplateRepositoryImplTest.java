package com.rdfgroup.cms.services.admin.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.argThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.util.ReflectionTestUtils;

import com.rdfgroup.cms.services.domain.Website;
import com.rdfgroup.cms.services.domain.template.Template;

public class AdminTemplateRepositoryImplTest {

    private static final class TemplateQueryKeyMatcher extends ArgumentMatcher<Query> {
        
        public TemplateQueryKeyMatcher(Collection<String> keysToMatch) {
            this.keysToMatch = keysToMatch;
        }
        
        private final Collection<String> keysToMatch;
        
        @SuppressWarnings("unchecked")
        @Override 
        public boolean matches(Object argument) {
            Query query = (Query) argument;
            Map<String, Object> queryMap = query.getQueryObject().toMap();
            
            return queryMap.keySet().containsAll(keysToMatch);
        }
        
        public static TemplateQueryKeyMatcher hasKeys(String... keysToMatch) {
            return new TemplateQueryKeyMatcher(Arrays.asList(keysToMatch));
        }
    }

    @Mock
    private MongoTemplate mongoTemplate;
    
    private AdminTemplateRepositoryImpl repo;
    
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        
        repo = new AdminTemplateRepositoryImpl();
        ReflectionTestUtils.setField(repo, "mongoTemplate", mongoTemplate);
    }
    
    @Test
    public void getTemplates_all() throws Exception {
        List<Template> allTemplates = Arrays.asList(new Template());
        when(mongoTemplate.findAll(Template.class)).thenReturn(allTemplates);
        
        List<Template> result = repo.getTemplates(Website.getWebsite(Website.ALL_WEBSITES));
        
        assertNotNull(result);
        assertEquals(allTemplates, result);
    }
    
    @Test
    public void getTemplates_specific() throws Exception {
        List<Template> enTemplates = Arrays.asList(new Template());
        when(mongoTemplate.find((Query)anyObject(), eq(Template.class))).thenReturn(enTemplates);
        
        List<Template> result = repo.getTemplates(Website.getWebsite(Website.GB_WEBSITE));
        
        assertNotNull(result);
        assertEquals(enTemplates, result);
    }
    
    @Test
    public void getTemplate() throws Exception {
        Template template = new Template();
        String id = "123435";
        when(mongoTemplate.findById(id, Template.class)).thenReturn(template);
        
        Template result = repo.getTemplate(id);
        
        assertNotNull(result);
        assertEquals(template, result);
    }
    
    @Test
    public void updateTemplate() throws Exception {
        Template template = new Template();
        
        Template result = repo.updateTemplate(template);
        
        verify(mongoTemplate).save(template);
        
        assertNotNull(result);
    }
    
    @Test
    public void getTemplateByType_specific() throws Exception {
        
        when(mongoTemplate.find(argThat(TemplateQueryKeyMatcher.hasKeys("type", "website")), eq(Template.class))).thenReturn(Arrays.asList(new Template()));
        
        List<Template> result = repo.getTemplateByType("type", Website.getWebsite(Website.GB_WEBSITE));
        
        assertNotNull(result);
        assertEquals(1, result.size());
    }
    
    @Test
    public void getTemplateByType_allWebsites() throws Exception {
        
        when(mongoTemplate.find(argThat(TemplateQueryKeyMatcher.hasKeys("type")), eq(Template.class))).thenReturn(Arrays.asList(new Template()));
        
        List<Template> result = repo.getTemplateByType("type", Website.getWebsite(Website.ALL_WEBSITES));
        
        assertNotNull(result);
        assertEquals(1, result.size());
    }
    
    @Test
    public void getTemplatesByKey_specific() throws Exception {
        
        when(mongoTemplate.find(argThat(TemplateQueryKeyMatcher.hasKeys("key", "website")), eq(Template.class))).thenReturn(Arrays.asList(new Template()));
        
        List<Template> result = repo.getTemplatesByKey("key", Website.getWebsite(Website.GB_WEBSITE));
        
        assertNotNull(result);
        assertEquals(1, result.size());
        
    }
    
    @Test
    public void getTemplatesByKey_allWebsites() throws Exception {
        
        when(mongoTemplate.find(argThat(TemplateQueryKeyMatcher.hasKeys("key")), eq(Template.class))).thenReturn(Arrays.asList(new Template()));
        
        List<Template> result = repo.getTemplatesByKey("key", Website.getWebsite(Website.ALL_WEBSITES));
        
        assertNotNull(result);
        assertEquals(1, result.size());
    }
    
    @Test
    public void deleteTemplate() throws Exception {
        
        String id = "123456";
        Template template = new Template();
        when(mongoTemplate.findById(id, Template.class)).thenReturn(template);
        repo.deleteTemplate(id);
        
        verify(mongoTemplate).remove(template);
    }
}
