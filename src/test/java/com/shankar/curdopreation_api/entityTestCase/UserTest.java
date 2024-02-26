package com.shankar.curdopreation_api.entityTestCase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.Test;

import com.shankar.curdopreation_api.entity.User;

public class UserTest {

	@Test
	public void testGetterAndSetter() {
		User user = new User();
		user.setId(1);
		user.setName("John Doe");
		user.setEmail("john@example.com");
		user.setMobileNo(1234567890L);

		assertEquals(1, user.getId());
		assertEquals("John Doe", user.getName());
		assertEquals("john@example.com", user.getEmail());
		assertEquals(1234567890L, user.getMobileNo());
	}

	@Test
	public void testEqualsAndHashCode() {
		User user1 = new User();
		user1.setId(1);
		User user2 = new User();
		user2.setId(1);
		User user3 = new User();
		user3.setId(2);
		assertEquals(user1, user2);
		assertNotEquals(user1, user3);
		assertEquals(user1.hashCode(), user2.hashCode());
		assertNotEquals(user1.hashCode(), user3.hashCode());
	}

	@Test
	void test_getterAndSetterusingDefaultConstructor() {
		User user = getUser();
		assertNotNull(user.getMobileNo());
		assertNotNull(user.getName());
		assertNotNull(user.getEmail());
		assertNotNull(user.getId());
	}

	private User getUser() {
		User user = new User();
		user.setId(1);
		user.setEmail("jimmygarg94.com");
		user.setName("jimmy123");
		user.setMobileNo(543456);
		return user;
	}

}
