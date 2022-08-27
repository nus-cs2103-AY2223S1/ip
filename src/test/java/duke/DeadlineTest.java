package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    LocalDate date = LocalDate.parse("2000-01-01");
    Deadline testEvent = new Deadline("test",date);

    @Test
    public void eventTestDone(){
        assertFalse(testEvent.getStatus());
    }

    @Test
    public void eventTestSetDone(){
        testEvent.setDone();
        assertTrue(testEvent.getStatus());
        testEvent.setNotDone();
        assertFalse(testEvent.getStatus());
    }


    @Test
    public void deadlineTestByThisDate() {
        LocalDate futureDate = LocalDate.parse("2001-01-01");
        LocalDate pastDate = LocalDate.parse("1999-01-01");
        assertTrue(testEvent.byThisDate(futureDate));
        assertFalse(testEvent.byThisDate(pastDate));
    }

    @Test
    public void deadlineTestParseTask() {
        assertEquals(testEvent.parseTask(),"DNtest/2000-01-01");
    }

    @Test
    public void deadlineTestToString() {
        assertEquals(testEvent.toString(),"[D][ ] test (by: Jan 1 2000)");
        testEvent.setDone();
        assertEquals(testEvent.toString(),"[D][X] test (by: Jan 1 2000)");
    }



}
