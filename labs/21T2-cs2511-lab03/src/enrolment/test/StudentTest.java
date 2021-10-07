package enrolment.test;

import enrolment.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.time.DayOfWeek;
import java.time.LocalTime;

public class StudentTest {

    @Test
    public void testGets() {
        Student bunny = new Student("z5555555");
        Student david = new Student("z1111111");

        assertEquals(bunny.getZID(), "z5555555");
        assertEquals(david.getZID(), "z1111111");
    }

    @Test
    public void testEnrolled() {

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

        CourseOffering comp1531Offering1 = new CourseOffering(comp1531, "19T1");
        Session comp1531Lec = new Lecture("Ground Floor", DayOfWeek.WEDNESDAY,
                LocalTime.of(8, 0), LocalTime.of(10,0 ), "Jason");
        comp1531Offering1.addSession(comp1531Lec);
        comp1531.addOffering(comp1531Offering1);

        assertTrue(bunny.getEnrolled(comp1511Offering1));
        assertFalse(bunny.getEnrolled(comp1531Offering1));
    }

    @Test
    public void TestReceiveGrade() {
        Course comp1511 = new Course("COMP1511", "Programming Fundamentals");
        Course comp1531 = new Course("COMP1531", "Software Engineering Fundamentals");
        comp1531.addPrereq(comp1511);

        Student bunny = new Student("z5555555");

        Session comp1511Lec = new Lecture("Ground Floor", DayOfWeek.FRIDAY,
                LocalTime.of(8, 0), LocalTime.of(10,0 ), "Jason");
        Session comp1511Tut1 =  new Tutorial("Ground Floor", DayOfWeek.THURSDAY,
                LocalTime.of(12, 0), LocalTime.of(14,0 ), "Jason");
        CourseOffering comp1511Offering1 = new CourseOffering(comp1511, "19T1");
        comp1511Offering1.addSession(comp1511Lec);
        comp1511Offering1.addSession(comp1511Tut1);
        comp1511.addOffering(comp1511Offering1);

        CourseOffering comp1531Offering1 = new CourseOffering(comp1531, "19T1");
        Session comp1531Lec = new Lecture("Ground Floor", DayOfWeek.WEDNESDAY,
                LocalTime.of(8, 0), LocalTime.of(10,0 ), "Jason");
        comp1531Offering1.addSession(comp1531Lec);
        comp1531.addOffering(comp1531Offering1);



        Grade bunnyGradeComp1511 = new Grade(80, "D");

        bunny.getEnrolled(comp1511Offering1);
        bunny.getGrade(comp1511Offering1);
        bunny.getGrade(comp1531Offering1);
        bunny.receiveGrade(comp1511Offering1, bunnyGradeComp1511);

        assertEquals(bunny.getGrade(comp1511Offering1), "D");

    }


}
