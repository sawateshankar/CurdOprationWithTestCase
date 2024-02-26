package com.shankar.curdopreation_api.controllerTestCase;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.shankar.curdopreation_api.controller.UserController;
import com.shankar.curdopreation_api.entity.User;
import com.shankar.curdopreation_api.service.UserService;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
public class UserControllerTest {
	
	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
    
    @Test
    public void testCreateUser() throws Exception {
        // Create a sample user object
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john@example.com");
        user.setMobileNo(1234567890L);
        // Stub the userService.createUser() method to return the sample user
        when(userService.createUser(any(User.class))).thenReturn(user);
        // Perform a POST request to create a user
        mockMvc.perform(post("/user/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"John Doe\", \"email\": \"john@example.com\", \"mobileNo\": 1234567890}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john@example.com"))
                .andExpect(jsonPath("$.mobileNo").value(1234567890L));
        // Verify that the userService.createUser() method was called once with the sample user
        verify(userService, times(1)).createUser(any(User.class));
    }

    
    @Test
    public void testGetAllUsers() throws Exception {
        // Create sample user objects
        User user1 = new User();
        user1.setId(1);
        user1.setName("John Doe");
        user1.setEmail("john@example.com");
        user1.setMobileNo(1234567890L);

        User user2 = new User();
        user2.setId(2);
        user2.setName("Jane Doe");
        user2.setEmail("jane@example.com");
        user2.setMobileNo(9876543210L);

        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);

        // Stub the userService.getAllUser() method to return the sample user list
        when(userService.getAllUser()).thenReturn(userList);

        // Perform a GET request to retrieve all users
        mockMvc.perform(get("/user/all")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[0].email").value("john@example.com"))
                .andExpect(jsonPath("$[0].mobileNo").value(1234567890))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Jane Doe"))
                .andExpect(jsonPath("$[1].email").value("jane@example.com"))
                .andExpect(jsonPath("$[1].mobileNo").value(9876543210L));

        // Verify that the userService.getAllUser() method was called once
        verify(userService, times(1)).getAllUser();
    }
    
    @Test
    public void testGetSingleUser() throws Exception {
        // Create a sample user object
        User user = new User();
        user.setId(1);
        user.setName("John Doe");
        user.setEmail("john@example.com");
        user.setMobileNo(1234567890L);

        // Stub the userService.getUser() method to return the sample user when called with ID 1
        when(userService.getUser(1)).thenReturn(user);

        // Perform a GET request to retrieve the user with ID 1
        mockMvc.perform(get("/user/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john@example.com"))
                .andExpect(jsonPath("$.mobileNo").value(1234567890));

        // Verify that the userService.getUser() method was called once with ID 1
        verify(userService, times(1)).getUser(1);
    }
    
    @Test
    public void testUpdateUsers() throws Exception {
        // Create a sample user object
        User updatedUser = new User();
        updatedUser.setId(1);
        updatedUser.setName("John Updated");
        updatedUser.setEmail("john_updated@example.com");
        updatedUser.setMobileNo(9876543210L);

        // Stub the userService.updateUser() method to return the updated user when called with ID 1 and the updated user object
        when(userService.updateUser(eq(updatedUser), eq(1))).thenReturn(updatedUser);

        // Perform a PUT request to update the user with ID 1
        mockMvc.perform(put("/user/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": 1, \"name\": \"John Updated\", \"email\": \"john_updated@example.com\", \"mobileNo\": 9876543210}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John Updated"))
                .andExpect(jsonPath("$.email").value("john_updated@example.com"))
                .andExpect(jsonPath("$.mobileNo").value(9876543210L));

        // Verify that the userService.updateUser() method was called once with the updated user and ID 1
        verify(userService, times(1)).updateUser(eq(updatedUser), eq(1));
    }
    
    @Test
    public void testDeleteUser() throws Exception {
        // Perform a DELETE request to delete the user with ID 1
        mockMvc.perform(delete("/user/delete/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Verify that the userService.deleteUser() method was called once with ID 1
        verify(userService, times(1)).deleteUser(1);
    }
    
}
