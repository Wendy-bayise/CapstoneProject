package za.ac.cput.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import za.ac.cput.domain.Admin;
import za.ac.cput.factory.AdminFactory;
import za.ac.cput.repository.AdminRepository;
import za.ac.cput.service.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/*
AdminControllerTest.java
AdminControllerTest
Author: Thimna Barbara Booi - 230232108
Date: 28/06/2026
 */


@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
@Transactional
class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

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
    @DisplayName("register admin successfully")
    void testRegisterAdmin_Success() throws Exception {
        mockMvc.perform(post("/admin/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(admin)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.adminId", is(adminId)))
                .andExpect(jsonPath("$.email", is(email)))
                .andExpect(jsonPath("$.firstName", is("John")))
                .andExpect(jsonPath("$.lastName", is("Doe")))
                .andExpect(jsonPath("$.role", is("ADMIN")))
                .andExpect(jsonPath("$.createdAt", notNullValue()));
    }

    @Test
    @DisplayName("return 400 when registering admin with invalid data")
    void testRegisterAdmin_InvalidData_ThrowsException() throws Exception {
        Admin invalidAdmin = AdminFactory.createAdmin(
                null, // Invalid adminId
                "John",
                "Doe",
                email,
                password,
                "ADMIN"
        );

        mockMvc.perform(post("/admin/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidAdmin)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("login admin successfully")
    void testLoginAdmin_Success() throws Exception {
        adminService.create(admin);

        mockMvc.perform(post("/admin/login")
                        .param("email", email)
                        .param("password", password))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.adminId", is(adminId)))
                .andExpect(jsonPath("$.email", is(email)))
                .andExpect(jsonPath("$.lastLogin", notNullValue()));
    }


    @Test
    @DisplayName("get all admins successfully")
    void testGetAllAdmins_Success() throws Exception {
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

        mockMvc.perform(get("/admin/admins"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].adminId", is(adminId)))
                .andExpect(jsonPath("$[1].adminId", is("ADM002")));
    }

    @Test
    @DisplayName("get admin by id successfully")
    void testGetAdminById_Success() throws Exception {
        adminService.create(admin);

        mockMvc.perform(get("/admin/admins/{adminId}", adminId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.adminId", is(adminId)))
                .andExpect(jsonPath("$.email", is(email)))
                .andExpect(jsonPath("$.firstName", is("John")));
    }

    @Test
    @DisplayName("return 404 when admin not found by id")
    void testGetAdminById_NotFound_Returns404() throws Exception {
        String nonExistentId = "NONEXISTENT";

        mockMvc.perform(get("/admin/admins/{adminId}", nonExistentId))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("update admin successfully")
    void testUpdateAdmin_Success() throws Exception {
        adminService.create(admin);

        Admin updatedAdmin = AdminFactory.createAdmin(
                adminId,
                "Jonathan",
                "Smith",
                "jonathan.smith@example.com",
                "NewPassword123",
                "SUPER_ADMIN"
        );

        mockMvc.perform(put("/admin/admins")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedAdmin)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.adminId", is(adminId)))
                .andExpect(jsonPath("$.firstName", is("Jonathan")))
                .andExpect(jsonPath("$.lastName", is("Smith")))
                .andExpect(jsonPath("$.email", is("jonathan.smith@example.com")))
                .andExpect(jsonPath("$.role", is("SUPER_ADMIN")));
    }


    @Test
    @DisplayName("delete admin successfully")
    void testDeleteAdmin_Success() throws Exception {
        adminService.create(admin);

        mockMvc.perform(delete("/admin/admins/{adminId}", adminId))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("return 404 when deleting non-existent admin")
    void testDeleteAdmin_NotFound_Returns404() throws Exception {
        String nonExistentId = "NONEXISTENT";

        mockMvc.perform(delete("/admin/admins/{adminId}", nonExistentId))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("get dashboard successfully")
    void testGetDashboard_Success() throws Exception {
        // Create test data
        adminService.create(admin);

        mockMvc.perform(get("/admin/dashboard"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.stats", notNullValue()))
                .andExpect(jsonPath("$.tutorSubjectSummaries", notNullValue()))
                .andExpect(jsonPath("$.studentBookingSummaries", notNullValue()));
    }

    @Test
    @DisplayName("get dashboard stats successfully")
    void testGetDashboardStats_Success() throws Exception {
        adminService.create(admin);

        mockMvc.perform(get("/admin/dashboard/stats"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalStudents", is(0)))
                .andExpect(jsonPath("$.totalTutors", is(0)))
                .andExpect(jsonPath("$.totalSubjects", is(0)))
                .andExpect(jsonPath("$.totalBookings", is(0)));
    }

    @Test
    @DisplayName("get tutor subject summaries successfully")
    void testGetTutorSubjectSummaries_Success() throws Exception {
        mockMvc.perform(get("/admin/dashboard/tutors-subjects"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()));
    }

    @Test
    @DisplayName("get student booking summaries successfully")
    void testGetStudentBookingSummaries_Success() throws Exception {
        mockMvc.perform(get("/admin/dashboard/students-bookings"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()));
    }
}