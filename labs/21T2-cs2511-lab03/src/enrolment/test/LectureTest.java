package enrolment.test;

import enrolment.Lecture;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.time.DayOfWeek;
import java.time.LocalTime;

public class LectureTest {

    @Test
    public void testGets() {

        Lecture lecture1 = new Lecture("UnderGround", DayOfWeek.FRIDAY,
                LocalTime.of(10, 0), LocalTime.of(14, 0), "bunny");

        Lecture lecture2 = new Lecture("Floor", DayOfWeek.WEDNESDAY,
                LocalTime.of(8, 0), LocalTime.of(12, 0), "jason");

        assertEquals(lecture1.getLecturer(), "bunny");
        assertEquals(lecture2.getLecturer(), "jason");

    }

}
