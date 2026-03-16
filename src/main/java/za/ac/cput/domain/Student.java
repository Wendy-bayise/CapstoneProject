package za.ac.cput.domain;
/*
Student.java
Student POJO with builder
Author: Sabelo Ceza - 220094489
Date: 13/03/2026
 */

public class Student {
    private String studentNumber;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private String levelOfStudy;

    private Student(){
    }
    private Student(Builder builder){
        this.studentNumber = builder.studentNumber;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
        this.password = builder.password;
        this.levelOfStudy = builder.levelOfStudy;

    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmail() {
        return email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getPassword() {
        return password;
    }
    public String getLevelOfStudy() {
        return levelOfStudy;
    }

    @Override
    public String toString() {
        return "==Student Details==" +
                "\nstudent Number: " + studentNumber +
                "\nFirst Name: " + firstName +
                "\nLast Name: " + lastName +
                "\nEmail: " + email +
                "\nPhone Number: " + phoneNumber +
                "\nLevel Of Study: " + levelOfStudy +
                "\nPassword: " + password;
    }
    public static class Builder{
        private String studentNumber;
        private String firstName;
        private String lastName;
        private String email;
        private String phoneNumber;
        private String password;
        private String levelOfStudy;

        public Builder setStudentNumber(String studentNumber) {
            this.studentNumber = studentNumber;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }
        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }
        public Builder setLevelOfStudy(String levelOfStudy) {
            this.levelOfStudy = levelOfStudy;
            return this;
        }
        public Builder copy(Student student){
            this.studentNumber = student.studentNumber;
            this.firstName = student.firstName;
            this.lastName = student.lastName;
            this.email = student.email;
            this.phoneNumber = student.phoneNumber;
            this.password = student.password;
            this.levelOfStudy = student.levelOfStudy;
            return this;
        }
        public Student build(){
            return new Student(this);
        }


    }


}
