package za.ac.cput.factory;
import za.ac.cput.domain.Admin;
import za.ac.cput.util.Helper;
/*
AdminFactory.java
Admin with factory class
Author: Thimna Booi - 230232108
Date: 16/03/2026
 */

import za.ac.cput.domain.Admin;
import za.ac.cput.util.Helper;
import java.time.LocalDateTime;

public class AdminFactory {

    public static Admin createAdmin(String adminId, String firstName, String lastName,
                                    String email, String password, String role) {

        if (Helper.isNullOrEmpty(adminId) ||
                Helper.isNullOrEmpty(firstName) ||
                Helper.isNullOrEmpty(lastName) ||
                Helper.isNullOrEmpty(email) ||
                Helper.isNullOrEmpty(password) ||
                Helper.isNullOrEmpty(role)) {
            return null;
        }

        if (!Helper.isValidEmail(email)) {
            return null;
        }

        return new Admin.Builder()
                .setAdminId(adminId)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPassword(password)
                .setRole(role)
                .setCreatedAt(LocalDateTime.now())
                .build();
    }


    public static Admin createAdmin(String adminId, String firstName, String lastName,
                                    String email, String password, String role,
                                    LocalDateTime createdAt) {
        if (Helper.isNullOrEmpty(adminId) ||
                Helper.isNullOrEmpty(firstName) ||
                Helper.isNullOrEmpty(lastName) ||
                Helper.isNullOrEmpty(email) ||
                Helper.isNullOrEmpty(password) ||
                Helper.isNullOrEmpty(role)) {
            return null;
        }

        if (!Helper.isValidEmail(email)) {
            return null;
        }

        return new Admin.Builder()
                .setAdminId(adminId)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPassword(password)
                .setRole(role)
                .setCreatedAt(createdAt != null ? createdAt : LocalDateTime.now())
                .build();
    }


    public static Admin createAdminWithLogin(String adminId, String firstName, String lastName,
                                             String email, String password, String role,
                                             LocalDateTime lastLogin) {
        if (Helper.isNullOrEmpty(adminId) ||
                Helper.isNullOrEmpty(firstName) ||
                Helper.isNullOrEmpty(lastName) ||
                Helper.isNullOrEmpty(email) ||
                Helper.isNullOrEmpty(password) ||
                Helper.isNullOrEmpty(role)) {
            return null;
        }

        if (!Helper.isValidEmail(email)) {
            return null;
        }

        return new Admin.Builder()
                .setAdminId(adminId)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPassword(password)
                .setRole(role)
                .setCreatedAt(LocalDateTime.now())
                .setLastLogin(lastLogin)
                .build();
    }


    public static Admin createAdminFull(String adminId, String firstName, String lastName,
                                        String email, String password, String role,
                                        LocalDateTime createdAt, LocalDateTime lastLogin) {
        if (Helper.isNullOrEmpty(adminId) ||
                Helper.isNullOrEmpty(firstName) ||
                Helper.isNullOrEmpty(lastName) ||
                Helper.isNullOrEmpty(email) ||
                Helper.isNullOrEmpty(password) ||
                Helper.isNullOrEmpty(role)) {
            return null;
        }

        if (!Helper.isValidEmail(email)) {
            return null;
        }

        return new Admin.Builder()
                .setAdminId(adminId)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPassword(password)
                .setRole(role)
                .setCreatedAt(createdAt != null ? createdAt : LocalDateTime.now())
                .setLastLogin(lastLogin)
                .build();
    }


    public static Admin updateLastLogin(Admin admin) {
        if (Helper.isNull(admin)) {
            return null;
        }

        return new Admin.Builder()
                .copy(admin)
                .setLastLogin(LocalDateTime.now())
                .build();
    }


    public static Admin updateRole(Admin admin, String newRole) {
        if (Helper.isNull(admin) || Helper.isNullOrEmpty(newRole)) {
            return null;
        }

        return new Admin.Builder()
                .copy(admin)
                .setRole(newRole)
                .build();
    }

    // Method to update admin password
    public static Admin updatePassword(Admin admin, String newPassword) {
        if (Helper.isNull(admin) || Helper.isNullOrEmpty(newPassword)) {
            return null;
        }

        return new Admin.Builder()
                .copy(admin)
                .setPassword(newPassword)
                .build();
    }


    public static Admin updateProfile(Admin admin, String firstName, String lastName, String email) {
        if (Helper.isNull(admin) ||
                Helper.isNullOrEmpty(firstName) ||
                Helper.isNullOrEmpty(lastName) ||
                Helper.isNullOrEmpty(email)) {
            return null;
        }

        if (!Helper.isValidEmail(email)) {
            return null;
        }

        return new Admin.Builder()
                .copy(admin)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .build();
    }
}