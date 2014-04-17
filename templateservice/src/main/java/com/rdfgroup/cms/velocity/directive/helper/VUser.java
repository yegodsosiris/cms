
package com.rdfgroup.cms.velocity.directive.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "password",
    "surname",
    "roles",
    "username",
    "firstname",
    "id"
})
public class VUser {

    @JsonProperty("password")
    private String password;
    @JsonProperty("surname")
    private Object surname;
    @JsonProperty("roles")
    private List<String> roles = new ArrayList<String>();
    @JsonProperty("username")
    private String username;
    @JsonProperty("firstname")
    private Object firstname;
    @JsonProperty("id")
    private String id;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    @JsonProperty("password")
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonProperty("surname")
    public Object getSurname() {
        return surname;
    }

    @JsonProperty("surname")
    public void setSurname(Object surname) {
        this.surname = surname;
    }

    @JsonProperty("roles")
    public List<String> getRoles() {
        return roles;
    }

    @JsonProperty("roles")
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @JsonProperty("username")
    public String getUsername() {
        return username;
    }

    @JsonProperty("username")
    public void setUsername(String username) {
        this.username = username;
    }

    @JsonProperty("firstname")
    public Object getFirstname() {
        return firstname;
    }

    @JsonProperty("firstname")
    public void setFirstname(Object firstname) {
        this.firstname = firstname;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperties(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
