package za.ac.cput.service;
import za.ac.cput.domain.Admin;
import za.ac.cput.factory.AdminFactory;
import za.ac.cput.repository.AdminRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
AdminServiceTest.java
AdminServiceTest
Author: Thimna Booi - 230232108
Date: 25/06/2026
 */


@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
@Transactional
class AdminServiceTest {

    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminRepository adminRepository;

    private Admin admin;
    private String adminId;
    private String email;
    private String password;

    @BeforeEach
    void setUp() {
        // Clean up any existing data
        adminRepository.deleteAll();

        adminId = "ADM001";
        email = "john.doe@example.com";
        password = "SecurePass123";

        admin = AdminFactory.createAdmin(
                adminId,
                "John",
                "Doe",
                email,
                password,
                "ADMIN"
        );
    }

    @Test
    @DisplayName("create admin successfully")
    void testCreate_Success() {
        Admin createdAdmin = adminService.create(admin);

        assertNotNull(createdAdmin);
        assertEquals(adminId, createdAdmin.getAdminId());
        assertEquals(email, createdAdmin.getEmail());
        assertEquals("ADMIN", createdAdmin.getRole());
        assertNotNull(createdAdmin.getCreatedAt());

        // Verify it was saved in the database
        Admin savedAdmin = adminRepository.findById(adminId).orElse(null);
        assertNotNull(savedAdmin);
        assertEquals(adminId, savedAdmin.getAdminId());
    }

    @Test
    @DisplayName("throw exception when creating admin with existing email")
    void testCreate_EmailExists_ThrowsException() {
        // Create first admin
        adminService.create(admin);

        // Try to create another admin with same email
        Admin duplicateAdmin = AdminFactory.createAdmin(
                "ADM002",
                "Jane",
                "Doe",
                email, // Same email
                "DifferentPass123",
                "SUPER_ADMIN"
        );

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            adminService.create(duplicateAdmin);
        });

        assertEquals("Admin with email " + email + " already exists", exception.getMessage());
    }

    @Test
    @DisplayName("read admin by id successfully")
    void testRead_Success() {
        adminService.create(admin);

        Admin foundAdmin = adminService.read(adminId);

        assertNotNull(foundAdmin);
        assertEquals(adminId, foundAdmin.getAdminId());
        assertEquals(email, foundAdmin.getEmail());
    }

    @Test
    @DisplayName("return null when admin not found")
    void testRead_NotFound_ReturnsNull() {
        String nonExistentId = "NONEXISTENT";
        Admin foundAdmin = adminService.read(nonExistentId);

        assertNull(foundAdmin);
    }

    @Test
    @DisplayName("update admin successfully")
    void testUpdate_Success() {
        adminService.create(admin);

        Admin updatedAdmin = AdminFactory.createAdmin(
                adminId,
                "Jonathan",
                "Smith",
                "jonathan.smith@example.com",
                "NewPassword123",
                "SUPER_ADMIN"
        );

        Admin result = adminService.update(updatedAdmin);

        assertNotNull(result);
        assertEquals("Jonathan", result.getFirstName());
        assertEquals("Smith", result.getLastName());
        assertEquals("jonathan.smith@example.com", result.getEmail());
        assertEquals("SUPER_ADMIN", result.getRole());

        // Verify update in database
        Admin savedAdmin = adminRepository.findById(adminId).orElse(null);
        assertNotNull(savedAdmin);
        assertEquals("Jonathan", savedAdmin.getFirstName());
        assertEquals("SUPER_ADMIN", savedAdmin.getRole());
    }

    @Test
    @DisplayName("throw exception when updating non-existent admin")
    void testUpdate_NotFound_ThrowsException() {
        String nonExistentId = "NONEXISTENT";
        Admin nonExistentAdmin = AdminFactory.createAdmin(
                nonExistentId,
                "Jane",
                "Doe",
                "jane.doe@example.com",
                "Password123",
                "ADMIN"
        );

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            adminService.update(nonExistentAdmin);
        });

        assertEquals("Admin not found with ID: " + nonExistentId, exception.getMessage());
    }

    @Test
    @DisplayName("delete admin successfully")
    void testDelete_Success() {
        adminService.create(admin);

        boolean deleted = adminService.delete(adminId);

        assertTrue(deleted);

        // Verify it was deleted
        Admin deletedAdmin = adminRepository.findById(adminId).orElse(null);
        assertNull(deletedAdmin);
    }

    @Test
    @DisplayName("return false when deleting non-existent admin")
    void testDelete_NotFound_ReturnsFalse() {
        String nonExistentId = "NONEXISTENT";
        boolean deleted = adminService.delete(nonExistentId);

        assertFalse(deleted);
    }

    @Test
    @DisplayName("get all admins successfully")
    void testGetAll_Success() {
        adminService.create(admin);

        Admin admin2 = AdminFactory.createAdmin(
                "ADM002",
                "Jane",
                "Smith",
                "jane.smith@example.com",
                "Password456",
                "SUPER_ADMIN"
        );
        adminService.create(admin2);

        List<Admin> result = adminService.getAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(adminId, result.get(0).getAdminId());
        assertEquals("ADM002", result.get(1).getAdminId());
    }

    @Test
    @DisplayName("get empty list when no admins exist")
    void testGetAll_EmptyList() {
        List<Admin> result = adminService.getAll();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("login successfully with correct credentials")
    void testLogin_Success() {
        adminService.create(admin);

        Admin loggedInAdmin = adminService.login(email, password);

        assertNotNull(loggedInAdmin);
        assertEquals(adminId, loggedInAdmin.getAdminId());
        assertEquals(email, loggedInAdmin.getEmail());
        assertNotNull(loggedInAdmin.getLastLogin());

        Admin savedAdmin = adminRepository.findById(adminId).orElse(null);
        assertNotNull(savedAdmin);
        assertNotNull(savedAdmin.getLastLogin());
    }

    @Test
    @DisplayName("throw exception when login with incorrect password")
    void testLogin_IncorrectPassword_ThrowsException() {
        adminService.create(admin);
        String wrongPassword = "WrongPassword";

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            adminService.login(email, wrongPassword);
        });

        assertEquals("Invalid password", exception.getMessage());
    }

    @Test
    @DisplayName("throw exception when login with non-existent email")
    void testLogin_EmailNotFound_ThrowsException() {
        String nonExistentEmail = "nonexistent@example.com";

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            adminService.login(nonExistentEmail, password);
        });

        assertEquals("Admin not found with email: " + nonExistentEmail, exception.getMessage());
    }

    @Test
    @DisplayName("find admin by email successfully")
    void testFindByEmail_Success() {
        adminService.create(admin);

        Admin foundAdmin = adminService.findByEmail(email);

        assertNotNull(foundAdmin);
        assertEquals(adminId, foundAdmin.getAdminId());
        assertEquals(email, foundAdmin.getEmail());
    }

    @Test
    @DisplayName("return null when finding admin by non-existent email")
    void testFindByEmail_NotFound_ReturnsNull() {
        String nonExistentEmail = "nonexistent@example.com";
        Admin foundAdmin = adminService.findByEmail(nonExistentEmail);

        assertNull(foundAdmin);
    }

    @Test
    @DisplayName("check if email exists")
    void testExistsByEmail_Success() {
        adminService.create(admin);

        boolean exists = adminService.existsByEmail(email);

        assertTrue(exists);
    }

    @Test
    @DisplayName("return false when checking non-existent email")
    void testExistsByEmail_NotFound_ReturnsFalse() {
        String nonExistentEmail = "nonexistent@example.com";
        boolean exists = adminService.existsByEmail(nonExistentEmail);

        assertFalse(exists);
    }

    @Test
    @DisplayName("get admins by role successfully")
    void testGetByRole_Success() {
        adminService.create(admin);

        Admin admin2 = AdminFactory.createAdmin(
                "ADM002",
                "Jane",
                "Smith",
                "jane.smith@example.com",
                "Password456",
                "SUPER_ADMIN"
        );
        adminService.create(admin2);

        List<Admin> result = adminService.getByRole("ADMIN");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("ADMIN", result.get(0).getRole());
    }

    @Test
    @DisplayName("return empty list when no admins with role exist")
    void testGetByRole_NoAdmins_ReturnsEmptyList() {
        adminService.create(admin);

        List<Admin> result = adminService.getByRole("SUPER_ADMIN");

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}