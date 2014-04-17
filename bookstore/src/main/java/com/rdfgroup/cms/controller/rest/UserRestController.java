package com.rdfgroup.cms.controller.rest;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.rdfgroup.security.domain.User;
import com.rdfgroup.security.service.UserService;

@Controller
@RequestMapping("/rest")
@Secured("ROLE_User")
public class UserRestController extends BaseRestController {

	@Autowired
	private UserService userService;
	

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public @ResponseBody List<User> getUsers() {
		return userService.getUsers();
	}

	@RequestMapping(value="/user", method = RequestMethod.POST)
	public @ResponseBody User createUser(@RequestBody User user, ModelMap model) {
		return userService.createUser(user);
	}

	@RequestMapping(value="/user", method = RequestMethod.PUT)
	@Secured("ROLE_Admin")
	public @ResponseBody User updateUser(@RequestBody User user) {
		User result = userService.updateUser(user);
        return result;
	}

    @RequestMapping(value = "/user/current", method = RequestMethod.GET)
    public @ResponseBody User getLoggedInUser(Principal principal) {
        String username = principal.getName();
        User user = userService.getUserByUsername(username);
        if(user == null) {
            user = new User();
        }
        return user;        
    }

	@RequestMapping(value="/user/{id}", method=RequestMethod.DELETE)
	public @ResponseBody Boolean deleteUser(@PathVariable String id) {
		userService.deleteUser(id);
		return true;
	}

    
    @RequestMapping(value = "/user/logout", method = RequestMethod.GET)
    public @ResponseBody Boolean logout(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return true;
    }
}
