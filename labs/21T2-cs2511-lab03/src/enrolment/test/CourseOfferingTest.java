package enrolment.test;

import enrolment.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import java.time.DayOfWeek;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class CourseOfferingTest {



    @Test
    public void testAddSession() {
        Course comp1511 = new Course("COMP1511", "Programming Fundamentals");

        Session comp1511Lec = new Lecture("Ground Floor", DayOfWeek.FRIDAY,
                LocalTime.of(8, 0), LocalTime.of(10, 0), "Jason");
        Session comp1511Tut1 = new Tutorial("Ground Floor", DayOfWeek.THURSDAY,
                LocalTime.of(12, 0), LocalTime.of(14, 0), "Jason");

        CourseOffering comp1511Offering1 = new CourseOffering(comp1511, "19T1");

        ArrayList<Session> actual = new ArrayList<>(Arrays.asList(comp1511Lec, comp1511Tut1));
        comp1511Offering1.addSession(comp1511Lec);
        comp1511Offering1.addSession(comp1511Tut1);
        assertEquals(comp1511Offering1.getSessions(), actual);

    }
    @Test
    public void testAddEnrolment() {
        Course comp1511 = new Course("COMP1511", "Programming Fundamentals");
        Student bunny = new Student("z5555555");
        Student david = new Student("z111111");


        Session comp1511Lec = new Lecture("Ground Floor", DayOfWeek.FRIDAY,
                LocalTime.of(8, 0), LocalTime.of(10, 0), "Jason");
        Session comp1511Tut1 = new Tutorial("Ground Floor", DayOfWeek.THURSDAY,
                LocalTime.of(12, 0), LocalTime.of(14, 0), "Jason");

        CourseOffering comp1511Offering1 = new CourseOffering(comp1511, "19T1");
        comp1511Offering1.addSession(comp1511Lec);
        comp1511Offering1.addSession(comp1511Tut1);
        comp1511Offering1.addSession(null);

        assertEquals(comp1511Offering1.getTerm(), "19T1");

        Enrolment bunnyComp1511 = new Enrolment(comp1511Offering1, bunny);
        Enrolment davidComp1511 = new Enrolment(comp1511Offering1, david);

        comp1511Offering1.addEnrolment(bunnyComp1511);
        comp1511Offering1.addEnrolment(davidComp1511);
        ArrayList<Enrolment> actual = new ArrayList<>(Arrays.asList(bunnyComp1511, davidComp1511));
        assertEquals(comp1511Offering1.getEnrolments(), actual);
    }


}
