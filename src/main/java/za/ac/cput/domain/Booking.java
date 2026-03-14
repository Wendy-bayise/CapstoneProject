/*
Booking.java
Booking POJO with builder
Author: Charmaine Dlamini-222056401
Date: 13/03/2026
 */

package za.ac.cput.domain;

public class Booking {
    private String bookingId;
    private String sessionId;
    private String sessionTime;
    private String sessionType;
    private String duration;
    private String studentNumber;
    private String subjectCode;
    private String tutorId;

    private Booking() {

    }

    private Booking(Builder builder) {
        this.bookingId = builder.bookingId;
        this.sessionId = builder.sessionId;
        this.sessionTime = builder.sessionTime;
        this.sessionType = builder.sessionType;
        this.duration = builder.duration;
        this.studentNumber = builder.studentNumber;
        this.subjectCode = builder.subjectCode;
        this.tutorId = builder.tutorId;


    }

    public String getBookingId() {
        return bookingId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getSessionTime() {
        return sessionTime;
    }

    public String getSessionType() {
        return sessionType;
    }

    public String getDuration() {
        return duration;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public String getTutorId() {
        return tutorId;
    }


    @Override
    public String toString() {
        return "Booking" +
                "\nBooking Id: " + bookingId +
                "\nSession Id: " + sessionId +
                "\nSession Time: " + sessionTime +
                "\nSession Type: " + sessionType +
                "\nDuration: " + duration +
                "\nStudent Number: " + studentNumber +
                "\nSubject Code: " + subjectCode +
                "\nTutor Id: " + tutorId;
    }

    public static class Builder {
        private String bookingId;
        private String sessionId;
        private String sessionTime;
        private String sessionType;
        private String duration;
        private String studentNumber;
        private String subjectCode;
        private String tutorId;

        public Builder copy(Booking booking) {
            this.bookingId = booking.bookingId;
            this.sessionId = booking.sessionId;
            this.sessionTime = booking.sessionTime;
            this.sessionType = booking.sessionType;
            this.duration = booking.duration;
            this.studentNumber = booking.studentNumber;
            this.subjectCode = booking.subjectCode;
            this.tutorId = booking.tutorId;
            return this;
        }


        public void setBookingId(String bookingId) {
            this.bookingId = bookingId;
        }

        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }

        public void setSessionTime(String sessionTime) {
            this.sessionTime = sessionTime;
        }

        public void setSessionType(String sessionType) {
            this.sessionType = sessionType;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public void setStudentNumber(String studentNumber) {
            this.studentNumber = studentNumber;
        }

        public void setSubjectCode(String subjectCode) {
            this.subjectCode = subjectCode;
        }

        public void setTutorId(String tutorId) {
            this.tutorId = tutorId;
        }

        public Booking build() {
            return new Booking(this);
        }
    }
}