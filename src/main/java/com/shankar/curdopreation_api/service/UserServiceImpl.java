package com.shankar.curdopreation_api.service;

import java.util.List;
import java.util.stream.Collector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shankar.curdopreation_api.entity.User;
import com.shankar.curdopreation_api.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User createUser(User user) {
		User saveUser = this.userRepository.save(user);
		return saveUser;
	}

	@Override
	public List<User> getAllUser() {
		List<User> findAll = this.userRepository.findAll();
		return findAll;
	}

	@Override
	public User getUser(Integer id) {
		User user = this.userRepository.findById(id).orElseThrow(()->new RuntimeException("User Not found"));
		return user;
	}

	@Override
	public void deleteUser(Integer id) {
		User user = this.userRepository.findById(id).orElseThrow(()->new RuntimeException("User Not found"));
		this.userRepository.deleteById(id);
	}

	@Override
	public User updateUser(User user, Integer id) {
		User findUser = this.userRepository.findById(id).orElseThrow(()->new RuntimeException("User Not found"));
		findUser.setEmail(user.getEmail());
		findUser.setMobileNo(user.getMobileNo());
		findUser.setName(user.getName());
		return this.userRepository.save(findUser);
	}

}
