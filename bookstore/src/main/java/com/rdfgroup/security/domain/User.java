package com.rdfgroup.security.domain;

import java.util.List;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.rdfgroup.cms.services.domain.BaseEntity;
import com.rdfgroup.cms.util.JacksonHelper;

@Document
public class User extends BaseEntity {

	private static final long serialVersionUID = -2096532557238867900L;
	
	@Indexed(unique = true)
	private String username;
	private String password;
	private List<Role> roles;
	private String firstname;
	private String surname;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    @Override
    public String toString() {
    	return JacksonHelper.convertToJSON(this);
    }

}
