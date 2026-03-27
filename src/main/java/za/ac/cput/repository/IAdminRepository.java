package za.ac.cput.repository;

import za.ac.cput.domain.Admin;
import java.util.List;

/*
IAdminRepository.java
Admin repository
Author: Thimna Booi - 230232108
Date: 20/03/2026
 */

public interface IAdminRepository extends IRepository<Admin, String>{
   List<Admin> getAll();
}
