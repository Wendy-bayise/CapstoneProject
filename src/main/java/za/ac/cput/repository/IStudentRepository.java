package za.ac.cput.repository;
import za.ac.cput.domain.Student;
import java.util.List;
/*
IStudentRepository.java
Student repository
Author: Sabelo Ceza - 220094489
Date: 20/03/2026
 */
public interface IStudentRepository extends IRepository<Student, String> {
    List<Student> getAll();
}