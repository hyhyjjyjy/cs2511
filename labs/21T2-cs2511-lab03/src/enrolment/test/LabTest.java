package enrolment.test;

import enrolment.Lab;
//import jdk.vm.ci.meta.Local;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.time.DayOfWeek;
import java.time.LocalTime;

public class LabTest {

    @Test
    public void testGets() {

        Lab lab1 = new Lab("UnderGround", DayOfWeek.FRIDAY,
                LocalTime.of(10, 0), LocalTime.of(14, 0), "bunny", "david");

        Lab lab2 = new Lab("Floor", DayOfWeek.WEDNESDAY,
                LocalTime.of(8, 0), LocalTime.of(12, 0), "jason", "july");

        assertEquals(lab1.getLabAssistant(), "david");
        assertEquals(lab2.getLabAssistant(), "july");

        assertEquals(lab1.getTutor(), "bunny");
        assertEquals(lab2.getTutor(), "jason");
    }

    @Test
    public void testSets() {

        Lab lab1 = new Lab("UnderGround", DayOfWeek.FRIDAY,
                LocalTime.of(10, 0), LocalTime.of(14, 0), "bunny", "david");

        Lab lab2 = new Lab("Floor", DayOfWeek.WEDNESDAY,
                LocalTime.of(8, 0), LocalTime.of(12, 0), "jason", "july");

        assertEquals(lab1.getLabAssistant(), "david");
        assertEquals(lab2.getLabAssistant(), "july");
        assertEquals(lab1.getTutor(), "bunny");
        assertEquals(lab2.getTutor(), "jason");

        lab1.setTutor("david");
        lab2.setTutor("july");
        lab1.setLabAssistant("bunny");
        lab2.setLabAssistant("jason");

        assertEquals(lab1.getTutor(), "david");
        assertEquals(lab2.getTutor(), "july");
        assertEquals(lab1.getLabAssistant(), "bunny");
        assertEquals(lab2.getLabAssistant(), "jason");

    }

}
