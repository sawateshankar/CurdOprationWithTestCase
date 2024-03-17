package com.shankar.curdopreation_api.service;

import java.util.List;

import com.shankar.curdopreation_api.entity.User;

public interface UserService {

	public User createUser(User user);
	
	public List<User> getAllUser();
	
	public User getUser(Integer id);
	
	public void deleteUser(Integer id);
	
	public User updateUser(User user,Integer id);
	
}
