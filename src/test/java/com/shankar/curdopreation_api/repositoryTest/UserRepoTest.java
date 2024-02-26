package com.shankar.curdopreation_api.repositoryTest;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import com.shankar.curdopreation_api.entity.User;
import com.shankar.curdopreation_api.repository.UserRepository;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@ContextConfiguration
public class UserRepoTest {
	
	@Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindById() {
        // Given
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john@example.com");
        user.setMobileNo(1234567890L);
        entityManager.persistAndFlush(user);

        // When
        Optional<User> found = userRepository.findById(user.getId());

        // Then
        assertEquals(user.getName(), found.get().getName());
        assertEquals(user.getEmail(), found.get().getEmail());
        assertEquals(user.getMobileNo(), found.get().getMobileNo());
    }

    // Add more test cases as needed
}
