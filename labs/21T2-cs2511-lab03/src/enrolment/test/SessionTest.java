package enrolment.test;

import enrolment.Lecture;
import enrolment.Session;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.time.DayOfWeek;
import java.time.LocalTime;

public class SessionTest {

    @Test
    public void testGets() {

        Session session1 = new Session("UnderGround", DayOfWeek.FRIDAY,
                LocalTime.of(10, 0), LocalTime.of(14, 0));

        Session session2 = new Session("Floor", DayOfWeek.WEDNESDAY,
                LocalTime.of(8, 0), LocalTime.of(12, 0));

        assertEquals(session1.getDay(), DayOfWeek.FRIDAY);
        assertEquals(session2.getDay(), DayOfWeek.WEDNESDAY);

        assertEquals(session1.getEnd(), LocalTime.of(14, 0));
        assertEquals(session2.getEnd(), LocalTime.of(12, 0));

        assertEquals(session1.getStart(), LocalTime.of(10, 0));
        assertEquals(session2.getStart(), LocalTime.of(8, 0));

        assertEquals(session1.getLocation(), "UnderGround");
        assertEquals(session2.getLocation(), "Floor");

    }

}
