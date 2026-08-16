package za.ac.cput.service;
import za.ac.cput.domain.Admin;
import za.ac.cput.factory.AdminFactory;
import za.ac.cput.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

/*
AdminService.java
AdminService
Author: Thimna Booi - 230232108
Date: 17/06/2026
 */

@Service
public class AdminService implements IAdminService {

    private final AdminRepository repository;

    @Autowired
    public AdminService(AdminRepository repository) {
        this.repository = repository;
    }

    @Override
    public Admin create(Admin admin) {

        Admin newAdmin = AdminFactory.createAdmin(
                admin.getAdminId(),
                admin.getFirstName(),
                admin.getLastName(),
                admin.getEmail(),
                admin.getPassword(),
                admin.getRole()
        );

        if (newAdmin == null) {
            throw new RuntimeException("Invalid admin data");
        }

        if (repository.existsByEmail(admin.getEmail())) {
            throw new RuntimeException("Admin with email " + admin.getEmail() + " already exists");
        }

        return this.repository.save(newAdmin);
    }


    @Override
    public Admin read(String adminId) {
        return this.repository.findById(adminId).orElse(null);
    }

    @Override
    public Admin update(Admin admin) {
        Admin existing = this.repository.findById(admin.getAdminId())
                .orElseThrow(() -> new RuntimeException("Admin not found with ID: " + admin.getAdminId()));

        existing.setFirstName(admin.getFirstName());
        existing.setLastName(admin.getLastName());
        existing.setEmail(admin.getEmail());
        existing.setPassword(admin.getPassword());
        existing.setRole(admin.getRole());

        return this.repository.save(existing);
    }

    @Override
    public boolean delete(String adminId) {
        if (this.repository.existsById(adminId)) {
            this.repository.deleteById(adminId);
            return true;
        }
        return false;
    }

    @Override
    public List<Admin> getAll() {
        return this.repository.findAllOrderByCreatedAtDesc();
    }

    @Override
    public Admin login(String email, String password) {
        Admin admin = this.repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Admin not found with email: " + email));

        if (!admin.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }


        return this.repository.save(AdminFactory.updateLastLogin(admin));
    }

    @Override
    public Admin findByEmail(String email) {
        return this.repository.findByEmail(email).orElse(null);
    }

    @Override
    public boolean existsByEmail(String email) {
        return this.repository.existsByEmail(email);
    }

    @Override
    public List<Admin> getByRole(String role) {
        return this.repository.findByRole(role);
    }
}