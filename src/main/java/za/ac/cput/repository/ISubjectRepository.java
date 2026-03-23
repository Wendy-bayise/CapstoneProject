/*
ISubjectRepository
Author: Wendy Bayise-222828978
Date: 22/03/2026
 */
package za.ac.cput.repository;
import java.util.List;
import za.ac.cput.domain.Subject;

public interface ISubjectRepository extends IRepository<Subject, String> {

    List<Subject> getAll();
}