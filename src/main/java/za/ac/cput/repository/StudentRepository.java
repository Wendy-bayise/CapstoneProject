package za.ac.cput.repository;
import za.ac.cput.domain.Student;

import java.util.ArrayList;
import java.util.List;

/*
StudentRepository.java
Student repository
Author: Sabelo Ceza - 220094489
Date: 20/03/2026
 */
public class StudentRepository implements IStudentRepository{
    private static IStudentRepository repository = null;
    private List<Student> studentList;      //declare arrayList

    //Make a private constructor and initialize the studentList
    private StudentRepository(){
        studentList = new ArrayList<>();
    }
    //Make use of singleton and guard method
    public static IStudentRepository getRepository(){
        if(repository == null){             //if repository is null
            repository = new StudentRepository();       //create a new one
        }
        return repository;                          //if it exists, then return the old one
    }

    @Override
    public Student create(Student student) {                    //accept student object
        boolean success = studentList.add(student);             //add the student object into the list
        if(success){
            return student;                                     //if added, return the stored object
        }
        return null;                                            //if not added, return null
    }

    //We use linear search with for loop
    @Override
    public Student read(String studentNumber) {
        for(Student student : studentList){
            if(student.getStudentNumber().equals(studentNumber)){
                return student;
            }
        }
        return null;
    }

    @Override
    public Student update(Student student) {                //Note: Object is already in the list
        String studentNumber = student.getStudentNumber();     //search using a primary key
        Student oldStudent = read(studentNumber);               //call the read method to find the existing student
       if(oldStudent == null){
           return null;                                 //if the student do not exist
       }
       boolean success = studentList.remove(oldStudent);        //if existed, remove the old student
       if(success){
           if(studentList.add(student)){                //add the new student
               return student;
           }
       }
        return null;
    }
    @Override
    public boolean delete(String studentNumber) {
        Student studentToDelete = read(studentNumber);          //call the read method to look for the student to delete
            if(studentToDelete == null){
                return false;
            }
            return (studentList.remove(studentToDelete));

    }
    @Override
    public List<Student> getAll() {
        return studentList;
    }
}
