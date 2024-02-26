package com.shankar.curdopreation_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shankar.curdopreation_api.entity.User;
import com.shankar.curdopreation_api.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/create")
	public ResponseEntity<User> careateUsers(@RequestBody User user) {
		User createUser = this.userService.createUser(user);
		return new ResponseEntity<>(createUser, HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> allUser = this.userService.getAllUser();
		return new ResponseEntity<>(allUser, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getSingleUsers(@PathVariable("id") Integer id) {
		User user = this.userService.getUser(id);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<User> updateUsers(@RequestBody User user, @PathVariable("id") Integer id) {
		User updateUser = this.userService.updateUser(user, id);
		return new ResponseEntity<>(updateUser, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") Integer id) {
		this.userService.deleteUser(id);
		return new ResponseEntity<>("Uesr has been deleted", HttpStatus.OK);
	}

}
