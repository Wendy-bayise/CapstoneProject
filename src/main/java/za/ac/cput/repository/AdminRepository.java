package za.ac.cput.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Admin;
import java.util.List;
import java.util.Optional;
/*
AdminRepository.java
Admin repository
Author: Thimna Booi - 230232108
Date: 20/03/2026
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {

    Optional<Admin> findByEmail(String email);

    @Query("SELECT a FROM Admin a WHERE a.role = :role")
    List<Admin> findByRole(String role);

    boolean existsByEmail(String email);

    @Query("SELECT a FROM Admin a ORDER BY a.createdAt DESC")
    List<Admin> findAllOrderByCreatedAtDesc();
}