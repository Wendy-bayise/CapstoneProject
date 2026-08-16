package za.ac.cput.factory;

import org.junit.jupiter.api.*;
import za.ac.cput.domain.Admin;
import static org.junit.jupiter.api.Assertions.*;

/*
AdminFactoryTest.java
Admin factory testing
Author: Thimna Booi - 230232108
Date: 25/03/2026
 */

import za.ac.cput.domain.Admin;
import za.ac.cput.util.Helper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AdminFactoryTest {

    private String validAdminId;
    private String validFirstName;
    private String validLastName;
    private String validEmail;
    private String validPassword;
    private String validRole;
    private LocalDateTime validDateTime;

    @BeforeEach
    void setUp() {
        validAdminId = "ADM001";
        validFirstName = "John";
        validLastName = "Doe";
        validEmail = "john.doe@example.com";
        validPassword = "SecurePass123";
        validRole = "ADMIN";
        validDateTime = LocalDateTime.now();
    }

    @Test
    @DisplayName("Should create admin successfully with valid data")
    void testCreateAdmin_Success() {
        Admin admin = AdminFactory.createAdmin(
                validAdminId,
                validFirstName,
                validLastName,
                validEmail,
                validPassword,
                validRole
        );

        assertNotNull(admin);
        assertEquals(validAdminId, admin.getAdminId());
        assertEquals(validFirstName, admin.getFirstName());
        assertEquals(validLastName, admin.getLastName());
        assertEquals(validEmail, admin.getEmail());
        assertEquals(validPassword, admin.getPassword());
        assertEquals(validRole, admin.getRole());
        assertNotNull(admin.getCreatedAt());
        assertNull(admin.getLastLogin());
    }

    @Test
    @DisplayName("Should return null when adminId is null")
    void testCreateAdmin_NullAdminId_ReturnsNull() {
        Admin admin = AdminFactory.createAdmin(
                null,
                validFirstName,
                validLastName,
                validEmail,
                validPassword,
                validRole
        );

        assertNull(admin);
    }

    @Test
    @DisplayName("Should return null when adminId is empty")
    void testCreateAdmin_EmptyAdminId_ReturnsNull() {
        Admin admin = AdminFactory.createAdmin(
                "",
                validFirstName,
                validLastName,
                validEmail,
                validPassword,
                validRole
        );

        assertNull(admin);
    }

    @Test
    @DisplayName("Should return null when firstName is null")
    void testCreateAdmin_NullFirstName_ReturnsNull() {
        Admin admin = AdminFactory.createAdmin(
                validAdminId,
                null,
                validLastName,
                validEmail,
                validPassword,
                validRole
        );

        assertNull(admin);
    }

    @Test
    @DisplayName("Should return null when lastName is null")
    void testCreateAdmin_NullLastName_ReturnsNull() {
        Admin admin = AdminFactory.createAdmin(
                validAdminId,
                validFirstName,
                null,
                validEmail,
                validPassword,
                validRole
        );

        assertNull(admin);
    }

    @Test
    @DisplayName("Should return null when email is null")
    void testCreateAdmin_NullEmail_ReturnsNull() {
        Admin admin = AdminFactory.createAdmin(
                validAdminId,
                validFirstName,
                validLastName,
                null,
                validPassword,
                validRole
        );

        assertNull(admin);
    }

    @Test
    @DisplayName("Should return null when email is invalid")
    void testCreateAdmin_InvalidEmail_ReturnsNull() {
        Admin admin = AdminFactory.createAdmin(
                validAdminId,
                validFirstName,
                validLastName,
                "invalid-email",
                validPassword,
                validRole
        );

        assertNull(admin);
    }

    @Test
    @DisplayName("Should return null when password is null")
    void testCreateAdmin_NullPassword_ReturnsNull() {
        Admin admin = AdminFactory.createAdmin(
                validAdminId,
                validFirstName,
                validLastName,
                validEmail,
                null,
                validRole
        );

        assertNull(admin);
    }

    @Test
    @DisplayName("Should return null when role is null")
    void testCreateAdmin_NullRole_ReturnsNull() {
        Admin admin = AdminFactory.createAdmin(
                validAdminId,
                validFirstName,
                validLastName,
                validEmail,
                validPassword,
                null
        );

        assertNull(admin);
    }

    @Test
    @DisplayName("Should create admin with custom creation date")
    void testCreateAdmin_WithCustomCreatedAt_Success() {
        LocalDateTime customDate = LocalDateTime.of(2026, 1, 1, 10, 30);

        Admin admin = AdminFactory.createAdmin(
                validAdminId,
                validFirstName,
                validLastName,
                validEmail,
                validPassword,
                validRole,
                customDate
        );

        assertNotNull(admin);
        assertEquals(customDate, admin.getCreatedAt());
    }

    @Test
    @DisplayName("Should create admin with last login")
    void testCreateAdminWithLogin_Success() {
        LocalDateTime lastLogin = LocalDateTime.now().minusHours(2);

        Admin admin = AdminFactory.createAdminWithLogin(
                validAdminId,
                validFirstName,
                validLastName,
                validEmail,
                validPassword,
                validRole,
                lastLogin
        );

        assertNotNull(admin);
        assertEquals(lastLogin, admin.getLastLogin());
        assertNotNull(admin.getCreatedAt());
    }

    @Test
    @DisplayName("Should create admin with all fields")
    void testCreateAdminFull_Success() {
        LocalDateTime createdAt = LocalDateTime.of(2026, 1, 1, 10, 30);
        LocalDateTime lastLogin = LocalDateTime.now().minusHours(2);

        Admin admin = AdminFactory.createAdminFull(
                validAdminId,
                validFirstName,
                validLastName,
                validEmail,
                validPassword,
                validRole,
                createdAt,
                lastLogin
        );

        assertNotNull(admin);
        assertEquals(validAdminId, admin.getAdminId());
        assertEquals(validFirstName, admin.getFirstName());
        assertEquals(validLastName, admin.getLastName());
        assertEquals(validEmail, admin.getEmail());
        assertEquals(validPassword, admin.getPassword());
        assertEquals(validRole, admin.getRole());
        assertEquals(createdAt, admin.getCreatedAt());
        assertEquals(lastLogin, admin.getLastLogin());
    }

    @Test
    @DisplayName("Should update last login successfully")
    void testUpdateLastLogin_Success() {
        Admin admin = AdminFactory.createAdmin(
                validAdminId,
                validFirstName,
                validLastName,
                validEmail,
                validPassword,
                validRole
        );

        assertNull(admin.getLastLogin());

        Admin updatedAdmin = AdminFactory.updateLastLogin(admin);

        assertNotNull(updatedAdmin);
        assertNotNull(updatedAdmin.getLastLogin());
        assertEquals(admin.getAdminId(), updatedAdmin.getAdminId());
        assertEquals(admin.getFirstName(), updatedAdmin.getFirstName());
        assertEquals(admin.getLastName(), updatedAdmin.getLastName());
        assertEquals(admin.getEmail(), updatedAdmin.getEmail());
        assertEquals(admin.getPassword(), updatedAdmin.getPassword());
        assertEquals(admin.getRole(), updatedAdmin.getRole());
    }

    @Test
    @DisplayName("Should return null when updating last login with null admin")
    void testUpdateLastLogin_NullAdmin_ReturnsNull() {
        Admin admin = AdminFactory.updateLastLogin(null);
        assertNull(admin);
    }

    @Test
    @DisplayName("Should update role successfully")
    void testUpdateRole_Success() {
        Admin admin = AdminFactory.createAdmin(
                validAdminId,
                validFirstName,
                validLastName,
                validEmail,
                validPassword,
                validRole
        );

        String newRole = "SUPER_ADMIN";
        Admin updatedAdmin = AdminFactory.updateRole(admin, newRole);

        assertNotNull(updatedAdmin);
        assertEquals(newRole, updatedAdmin.getRole());
        assertEquals(admin.getAdminId(), updatedAdmin.getAdminId());
        assertEquals(admin.getFirstName(), updatedAdmin.getFirstName());
        assertEquals(admin.getLastName(), updatedAdmin.getLastName());
        assertEquals(admin.getEmail(), updatedAdmin.getEmail());
    }

    @Test
    @DisplayName("Should return null when updating role with null admin")
    void testUpdateRole_NullAdmin_ReturnsNull() {
        Admin admin = AdminFactory.updateRole(null, "SUPER_ADMIN");
        assertNull(admin);
    }

    @Test
    @DisplayName("Should return null when updating role with null role")
    void testUpdateRole_NullRole_ReturnsNull() {
        Admin admin = AdminFactory.createAdmin(
                validAdminId,
                validFirstName,
                validLastName,
                validEmail,
                validPassword,
                validRole
        );

        Admin updatedAdmin = AdminFactory.updateRole(admin, null);
        assertNull(updatedAdmin);
    }

    @Test
    @DisplayName("Should update password successfully")
    void testUpdatePassword_Success() {
        Admin admin = AdminFactory.createAdmin(
                validAdminId,
                validFirstName,
                validLastName,
                validEmail,
                validPassword,
                validRole
        );

        String newPassword = "NewSecurePass456";
        Admin updatedAdmin = AdminFactory.updatePassword(admin, newPassword);

        assertNotNull(updatedAdmin);
        assertEquals(newPassword, updatedAdmin.getPassword());
        assertEquals(admin.getAdminId(), updatedAdmin.getAdminId());
        assertEquals(admin.getFirstName(), updatedAdmin.getFirstName());
        assertEquals(admin.getLastName(), updatedAdmin.getLastName());
        assertEquals(admin.getEmail(), updatedAdmin.getEmail());
        assertEquals(admin.getRole(), updatedAdmin.getRole());
    }

    @Test
    @DisplayName("Should return null when updating password with null admin")
    void testUpdatePassword_NullAdmin_ReturnsNull() {
        Admin admin = AdminFactory.updatePassword(null, "NewPassword");
        assertNull(admin);
    }

    @Test
    @DisplayName("Should update profile successfully")
    void testUpdateProfile_Success() {
        Admin admin = AdminFactory.createAdmin(
                validAdminId,
                validFirstName,
                validLastName,
                validEmail,
                validPassword,
                validRole
        );

        String newFirstName = "Jonathan";
        String newLastName = "Smith";
        String newEmail = "jonathan.smith@example.com";

        Admin updatedAdmin = AdminFactory.updateProfile(admin, newFirstName, newLastName, newEmail);

        assertNotNull(updatedAdmin);
        assertEquals(newFirstName, updatedAdmin.getFirstName());
        assertEquals(newLastName, updatedAdmin.getLastName());
        assertEquals(newEmail, updatedAdmin.getEmail());
        assertEquals(admin.getAdminId(), updatedAdmin.getAdminId());
        assertEquals(admin.getPassword(), updatedAdmin.getPassword());
        assertEquals(admin.getRole(), updatedAdmin.getRole());
    }

    @Test
    @DisplayName("Should return null when updating profile with null admin")
    void testUpdateProfile_NullAdmin_ReturnsNull() {
        Admin admin = AdminFactory.updateProfile(null, "NewName", "NewLastName", "new@email.com");
        assertNull(admin);
    }

    @Test
    @DisplayName("Should return null when updating profile with null firstName")
    void testUpdateProfile_NullFirstName_ReturnsNull() {
        Admin admin = AdminFactory.createAdmin(
                validAdminId,
                validFirstName,
                validLastName,
                validEmail,
                validPassword,
                validRole
        );

        Admin updatedAdmin = AdminFactory.updateProfile(admin, null, validLastName, validEmail);
        assertNull(updatedAdmin);
    }

    @Test
    @DisplayName("Should return null when updating profile with invalid email")
    void testUpdateProfile_InvalidEmail_ReturnsNull() {
        Admin admin = AdminFactory.createAdmin(
                validAdminId,
                validFirstName,
                validLastName,
                validEmail,
                validPassword,
                validRole
        );

        Admin updatedAdmin = AdminFactory.updateProfile(admin, validFirstName, validLastName, "invalid-email");
        assertNull(updatedAdmin);
    }

    @Test
    @DisplayName("Should create admin with different roles")
    void testCreateAdmin_DifferentRoles() {
        String[] roles = {"ADMIN", "SUPER_ADMIN", "MANAGER"};

        for (String role : roles) {
            Admin admin = AdminFactory.createAdmin(
                    validAdminId + "_" + role,
                    validFirstName,
                    validLastName,
                    validEmail,
                    validPassword,
                    role
            );

            assertNotNull(admin);
            assertEquals(role, admin.getRole());
        }
    }

    @Test
    @DisplayName("Should handle whitespace in fields")
    void testCreateAdmin_WithWhitespace_ReturnsNull() {
        Admin admin = AdminFactory.createAdmin(
                "  ",
                "  ",
                "  ",
                "  ",
                "  ",
                "  "
        );

        assertNull(admin);
    }
}