package enrolment.test;

import enrolment.Tutorial;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.time.DayOfWeek;
import java.time.LocalTime;

public class TutorialTest {

    @Test
    public void testGets() {

        Tutorial tutorial1 = new Tutorial("UnderGround", DayOfWeek.FRIDAY,
                LocalTime.of(10, 0), LocalTime.of(14, 0), "bunny");

        Tutorial tutorial2 = new Tutorial("Floor", DayOfWeek.WEDNESDAY,
                LocalTime.of(8, 0), LocalTime.of(12, 0), "jason");

        assertEquals(tutorial1.getTutor(), "bunny");
        assertEquals(tutorial2.getTutor(), "jason");

    }
}
