package com.physioschedule.testfactory;

import com.physioschedule.model.User;

public class UserFactory {

    // Factory method to create a User with all fields.
    public static User createUser(Long id, String name, String email, String password, String phoneNumber, User.Role role) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhoneNumber(phoneNumber);
        user.setRole(role);
        return user;
    }

    /**
     * Factory method to create a PATIENT with default values.
     * - Name: John Doe
     * - Email: johndoe@test.com
     * - Password: password1234 (this will be encoded in tests)
     * - Phone: 123456789
     * - Role: PATIENT
     */
    public static User createDefaultPatient() {
        return createUser(1L, "John Doe", "johndoe@test.com", "password1234", "123456789", User.Role.PATIENT);
    }

    /**
     * Factory method to create a THERAPIST with default values.
     * - Name: Jane Smith
     * - Email: janesmith@test.com
     * - Password: password456 (this will be encoded in tests)
     * - Phone: 987654321
     * - Role: THERAPIST
     */
    public static User createDefaultTherapist() {
        return createUser(2L, "Jane Smith", "janesmith@test.com",
                "password456", "987654321", User.Role.THERAPIST);
    }

    public static User createDefaultUser() {
        return createDefaultPatient();
    }
}
