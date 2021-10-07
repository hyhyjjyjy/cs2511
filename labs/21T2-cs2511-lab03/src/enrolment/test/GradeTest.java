package enrolment.test;

import enrolment.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;



public class GradeTest {

    @Test
    public void testGets() {
        Grade bunnyGrade = new Grade(80, "D");
        Grade davidGrade = new Grade(70, "CR");
        Grade jasonGrade = new Grade(60, "P");

        assertEquals(bunnyGrade.getMark(), 80);
        assertEquals(bunnyGrade.getGrade(), "D");

        assertEquals(davidGrade.getMark(), 70);
        assertEquals(davidGrade.getGrade(), "CR");

        assertEquals(jasonGrade.getMark(), 60);
        assertEquals(jasonGrade.getGrade(), "P");

    }

}
