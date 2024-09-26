package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testIsOnDate() {
        Deadline deadline = new Deadline("task1", "02/09/2022 13:40");
        assertEquals(true, deadline.isOnDate("02/09/2022"));
        assertEquals(false, deadline.isOnDate("09/02/2022"));
        assertEquals(false, deadline.isOnDate("02/09/2021"));
    }

    @Test
    public void testGetDeadlineStr() {
        Deadline deadline1 = new Deadline("task1", "02/09/2022 13:40");
        assertEquals("Fri, 01:40PM 02 September 2022", deadline1.getDeadlineStr());
        Deadline deadline2 = new Deadline("task1", "13/10/2022 19:30");
        assertEquals("Thu, 07:30PM 13 October 2022", deadline2.getDeadlineStr());
    }
}
