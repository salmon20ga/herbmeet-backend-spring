package hubmeet.backend.service;

import hubmeet.backend.domain.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class User2ServiceTest {
    @Autowired
    private UserService userService;

    @Test
    void saveUser() {
        // Given
        Users user = new Users();
        user.setUserID("test_user");
        user.setPassword("password");
        user.setEmail("test@example.com");
        user.setPhone("123456789");

        // When
        userService.saveUser(user);

        // Then
        assertNotNull(user.getUserNUM());
    }

    @Test
    void findUserById() {
        // Given
        Users user = new Users();
        user.setUserID("test_user");
        user.setPassword("password");
        user.setEmail("test@example.com");
        user.setPhone("123456789");
        userService.saveUser(user);

        // When
        Users foundUser = userService.findUserById(user.getUserNUM());

        // Then
        assertNotNull(foundUser);
        assertEquals(user.getUserNUM(), foundUser.getUserNUM());
    }

    @Test
    void findAllUsers() {
        // Given
        Users user1 = new Users();
        user1.setUserID("user1");
        user1.setPassword("password1");
        user1.setEmail("user1@example.com");
        user1.setPhone("111111111");
        userService.saveUser(user1);

        Users user2 = new Users();
        user2.setUserID("user2");
        user2.setPassword("password2");
        user2.setEmail("user2@example.com");
        user2.setPhone("222222222");
        userService.saveUser(user2);

        // When
        List<Users> users = userService.findAllUsers();

        // Then
        assertEquals(2, users.size());
    }

    @Test
    void findUserByEmail() {
        // Given
        Users user = new Users();
        user.setUserID("test_user");
        user.setPassword("password");
        user.setEmail("test@example.com");
        user.setPhone("123456789");
        userService.saveUser(user);

        // When
        Users foundUser = userService.findUserByEmail(user.getEmail());

        // Then
        assertNotNull(foundUser);
        assertEquals(user.getUserNUM(), foundUser.getUserNUM());
    }
}