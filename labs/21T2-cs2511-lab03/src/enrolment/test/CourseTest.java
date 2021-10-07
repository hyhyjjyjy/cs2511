package enrolment.test;

import enrolment.*;

import java.time.DayOfWeek;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class CourseTest {

    @Test
    public void testMeetingRequisite() {
        Course comp1511 = new Course("COMP1511", "Programming Fundamentals");
        Course comp1531 = new Course("COMP1531", "Software Engineering Fundamentals");
        comp1531.addPrereq(comp1511);
        Course comp2521 = new Course("COMP2521", "Data Structures and Algorithms");
        comp2521.addPrereq(comp1511);
        Course comp2511 = new Course("COMP2511", "Oriented Object Programming");
        comp2511.addPrereq(comp1511);
        comp2511.addPrereq(comp1531);

        Student bunny = new Student("z5555555");



        Session comp1511Lec = new Lecture("Ground Floor", DayOfWeek.FRIDAY,
                LocalTime.of(8, 0), LocalTime.of(10,0 ), "Jason");
        Session comp1511Tut1 =  new Tutorial("Ground Floor", DayOfWeek.THURSDAY,
                LocalTime.of(12, 0), LocalTime.of(14,0 ), "Jason");

        CourseOffering comp1511Offering1 = new CourseOffering(comp1511, "19T1");
        comp1511Offering1.addSession(comp1511Lec);
        comp1511Offering1.addSession(comp1511Tut1);
        comp1511.addOffering(comp1511Offering1);

        Session comp1531Lec = new Lecture("Ground Floor", DayOfWeek.FRIDAY,
                LocalTime.of(8, 0), LocalTime.of(10,0 ), "Jason");
        CourseOffering comp1531Offering1 = new CourseOffering(comp1531, "19T1");
        comp1531Offering1.addSession(comp1531Lec);
        comp1531.addOffering(comp1531Offering1);

        Session comp2521Lec = new Lecture("Ground Floor", DayOfWeek.FRIDAY,
                LocalTime.of(8, 0), LocalTime.of(10,0 ), "Jason");
        CourseOffering comp2521Offering1 = new CourseOffering(comp2511, "19T1");
        comp2521Offering1.addSession(comp2521Lec);
        comp2521.addOffering(comp2521Offering1);


        assertTrue(comp1511Offering1.isMeetingPrerequisite(bunny));
        assertFalse(comp1531Offering1.isMeetingPrerequisite(bunny));
        // assertFalse(comp2521Offering1.isMeetingPrerequisite(bunny));

        // bunny.getEnrolled(comp1511Offering1);
        // assertFalse(comp1531Offering1.isMeetingPrerequisite(bunny));
        // assertFalse(comp2521Offering1.isMeetingPrerequisite(bunny));



    }

    @Test
    public void testGets() {
        Course comp1511 = new Course("COMP1511", "Programming Fundamentals");
        Course comp1531 = new Course("COMP1531", "Software Engineering Fundamentals");

        comp1511.setUoc(6);
        comp1531.setUoc(6);

        assertEquals(comp1511.getCourseCode(), "COMP1511");
        assertEquals(comp1531.getCourseCode(), "COMP1531");

        assertEquals(comp1511.getUOC(), 6);
        assertEquals(comp1531.getUOC(), 6);

        assertEquals(comp1511.getTitle(), "Programming Fundamentals");
        assertEquals(comp1531.getTitle(), "Software Engineering Fundamentals");

    }


}
