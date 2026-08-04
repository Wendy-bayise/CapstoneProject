package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Booking;
import za.ac.cput.domain.Payment;
import za.ac.cput.domain.Student;
import za.ac.cput.domain.Tutor;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/*
PaymentFactoryTest.java
Payment factory test
Author: Safiya Elmi
(240500598)
Date: 25/03/2026
*/

class PaymentFactoryTest {

    @Test
    void createPayment() {

        Student student = StudentFactory.createStudent(
                "220094489",
                "Sabelo",
                "Ceza",
                "220094489@mycput.ac.za",
                "073 985 1110",
                "SabieCeza2026",
                "Third year",
                new ArrayList<>()
        );

        Tutor tutor = TutorFactory.createTutor(
                "T001",
                "Imaan",
                "Achmat",
                "imaan@gmail.com",
                "0211377053",
                "password",
                150.0,
                new ArrayList<>());

        Booking booking = BookingFactory.createBooking(
                "B12345",
                "ADP362S",
                "Online",
                "2 hours",
                LocalDateTime.of(2026, 5, 20, 10, 30),
                student,
                tutor,
                null
        );

        Payment payment = PaymentFactory.createPayment(
                "PAY001",
                1500.00,
                LocalDateTime.now(),
                "Card",
                "Completed",
                booking
        );

        assertNotNull(payment);
        System.out.println(payment);
    }
}
