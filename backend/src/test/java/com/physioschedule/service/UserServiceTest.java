package com.physioschedule.service;

import com.physioschedule.exception.EmailAlreadyInUseException;
import com.physioschedule.model.User;
import com.physioschedule.repository.UserRepository;
import com.physioschedule.testfactory.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Tests for the UserService class.
 * <p>
 * The tests use a default user with the following properties:
 * - Name: John Doe
 * - Email: johndoe@test.com
 * - Password: password1234
 * - Phone: 123456789
 * - Role: PATIENT
 */
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUser_Success() {
        User newUser = UserFactory.createDefaultUser();

        when(userRepository.findByEmail("johndoe@test.com")).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArgument(0));

        User registeredUser = userService.registerUser(newUser);

        assertNotNull(registeredUser);
        assertEquals("John Doe", registeredUser.getName());
        assertEquals("johndoe@test.com", registeredUser.getEmail());
        verify(userRepository, times(1)).findByEmail("johndoe@test.com");
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testRegisteredUser_EmailAlreadyInUse() {
        User newUser = UserFactory.createDefaultUser();

        when(userRepository.findByEmail("johndoe@test.com")).thenReturn(Optional.of(new User()));

        EmailAlreadyInUseException exception = assertThrows(
                EmailAlreadyInUseException.class,
                () -> userService.registerUser(newUser)
        );

        assertEquals("Email is already in use.", exception.getMessage());
        verify(userRepository, times(1)).findByEmail("johndoe@test.com");
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testRegisterUser_PasswordIsEncoded() {
        User newUser = UserFactory.createDefaultUser();

        when(userRepository.findByEmail("johndoe@test.com")).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        User registeredUser = userService.registerUser(newUser);

        assertNotEquals("password1234", registeredUser.getPassword());
        assertTrue(registeredUser.getPassword().startsWith("$2a$") || // Bcrypt password format
                registeredUser.getPassword().startsWith("$2b$") ||
                registeredUser.getPassword().startsWith("$2y$"));
        verify(userRepository, times(1)).save(any(User.class));
    }
}
