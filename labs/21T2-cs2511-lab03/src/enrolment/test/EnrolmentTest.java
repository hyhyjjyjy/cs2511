package enrolment.test;

import enrolment.*;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class EnrolmentTest {

    @Test
    public void testIntegration() {

        // Create courses
        Course comp1511 = new Course("COMP1511", "Programming Fundamentals");
        Course comp1531 = new Course("COMP1531", "Software Engineering Fundamentals");
        comp1531.addPrereq(comp1511);
        Course comp2521 = new Course("COMP2521", "Data Structures and Algorithms");
        comp2521.addPrereq(comp1511);

        CourseOffering comp1511Offering = new CourseOffering(comp1511, "19T1");
        CourseOffering comp1531Offering = new CourseOffering(comp1531, "19T1");
        CourseOffering comp2521Offering = new CourseOffering(comp2521, "19T2");

        // TODO Create some sessions for the courses
        Session comp1511lec = new Lecture("UnderGround", DayOfWeek.FRIDAY,
                LocalTime.of(10, 0), LocalTime.of(14, 0), "bunny");
        Session comp1511tut = new Tutorial("Floor", DayOfWeek.WEDNESDAY,
                LocalTime.of(8, 0), LocalTime.of(12, 0), "jason");
        comp1511Offering.addSession(comp1511lec);
        comp1511Offering.addSession(comp1511tut);
        comp1511.addOffering(comp1511Offering);

        Session comp1531lec = new Lecture("UnderGround", DayOfWeek.FRIDAY,
                LocalTime.of(10, 0), LocalTime.of(14, 0), "bunny");
        comp1531Offering.addSession(comp1531lec);
        comp1531.addOffering(comp1531Offering);

        Session comp2521lec = new Lecture("UnderGround", DayOfWeek.FRIDAY,
                LocalTime.of(10, 0), LocalTime.of(14, 0), "bunny");
        comp2521Offering.addSession(comp2521lec);
        comp2521.addOffering(comp2521Offering);

        // TODO Create a student
        Student dong = new Student("z123");
        Student ao = new Student("z342");


        // TODO Enrol the student in COMP1511 for T1 (this should succeed)
        assertTrue(dong.getEnrolled(comp1511Offering));
        assertTrue(ao.getEnrolled(comp1511Offering));

        // TODO Enrol the student in COMP1531 for T1 (this should fail as they
        // have not met the prereq)
        assertFalse(dong.getEnrolled(comp1531Offering));
        assertFalse(ao.getEnrolled(comp1531Offering));


        // TODO Give the student a passing grade for COMP1511
        Grade dong1511grade = new Grade(55, "PA");
        Grade ao1511grade = new Grade(40, "fail");
        dong.receiveGrade(comp1511Offering, dong1511grade);
        ao.receiveGrade(comp1511Offering, ao1511grade);


        // TODO Enrol the student in 2521 (this should succeed as they have met
        // the prereqs)
        assertTrue(dong.getEnrolled(comp2521Offering));
        assertFalse(ao.getEnrolled(comp2521Offering));

    }
}
