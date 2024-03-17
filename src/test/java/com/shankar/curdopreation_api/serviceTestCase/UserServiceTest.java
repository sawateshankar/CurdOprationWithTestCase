package com.shankar.curdopreation_api.serviceTestCase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.shankar.curdopreation_api.entity.User;
import com.shankar.curdopreation_api.repository.UserRepository;
import com.shankar.curdopreation_api.service.UserServiceImpl;

@SpringBootTest
public class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserServiceImpl userService;

	// CreateUserTestCase PostMapping method
	@Test
	public void testCreateUser() {
		// Given
		User user = new User();
		user.setId(1);
		user.setName("John Doe");
		user.setEmail("john@example.com");
		user.setMobileNo(1234567890L);

		when(userRepository.save(user)).thenReturn(user);

		// When
		User savedUser = userService.createUser(user);

		// Then
		assertEquals(user, savedUser);

		// Verify that userRepository.save() was called once with the correct user
		// object
		verify(userRepository, times(1)).save(user);
	}

	// GetAllUser TestCase GetMapping
	@Test
	public void testGetAllUser() {
		// Creating sample users
		List<User> users = new ArrayList<>();
		users.add(new User(1, "John Doe", "john@example.com", 1234567890L));
		users.add(new User(2, "Jane Doe", "jane@example.com", 9876543210L));

		when(userRepository.findAll()).thenReturn(users);
		// when
		List<User> allUser = userService.getAllUser();

		assertEquals(users.size(), allUser.size());

		for (int i = 0; i < users.size(); i++) {
			assertEquals(users.get(i), allUser.get(i));
		}
		// Verifying that userRepository.findAll() was called exactly once
		verify(userRepository, times(1)).findAll();
	}

	//GetSingle User GetMapping
	@Test
	public void testGetUser() {
		// Creating a sample user
		User sampleUser = new User(1, "John Doe", "john@example.com", 1234567890L);
		// Stubbing the findById() method to return the sample user when id=1 is passed
		when(userRepository.findById(1)).thenReturn(Optional.of(sampleUser));
		// Calling getUser() method with id=1
		User returnedUser = userService.getUser(1);
		// Verifying that the returned user matches the sample user
		assertEquals(sampleUser, returnedUser);
		// Verifying that userRepository.findById() was called exactly once with id=1
		verify(userRepository, times(1)).findById(1);
	}

	
	 @Test
	    public void testDeleteUser() {
	        // Creating a sample user ID
	        Integer userId = 1;
	        // Stubbing the findById() method to return a sample user when called with the sample ID
	        User sampleUser = new User(userId, "John Doe", "john@example.com", 1234567890L);
	        when(userRepository.findById(userId)).thenReturn(Optional.of(sampleUser));
	        // Calling deleteUser() method with the sample ID
	        userService.deleteUser(userId);
	        // Verifying that userRepository.deleteById() was called exactly once with the sample ID
	        verify(userRepository, times(1)).deleteById(userId);
	    }
	 
	 
	 @Test
	    public void testUpdateUser() {
	        // Creating a sample user and its updated version
	        User sampleUser = new User(1, "John Doe", "john@example.com", 1234567890L);
	        User updatedUser = new User(1, "Jane Doe", "jane@example.com", 9876543210L);
	        // Stubbing the findById() method to return the sample user when called with the sample ID
	        when(userRepository.findById(1)).thenReturn(Optional.of(sampleUser));
	        // Stubbing the save() method to return the updated user
	        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));
	        // Calling updateUser() method with the updated user and sample ID
	        User returnedUser = userService.updateUser(updatedUser, 1);
	        // Verifying that userRepository.save() was called exactly once with the updated user
	        verify(userRepository, times(1)).save(updatedUser);
	        // Verifying that the returned user matches the updated user
	        assertEquals(updatedUser, returnedUser);
	    }

}
