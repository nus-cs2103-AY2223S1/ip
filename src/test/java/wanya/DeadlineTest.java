package wanya;

import wanya.task.Deadline;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void deadlineToStringTest() {
        Deadline deadline = new Deadline("Homework", "2022-09-12 12:00");
        assertEquals("[D][ ] Homework(by: Sep 12 2022 12:00 PM)", deadline.toString());

        Deadline deadline2 = new Deadline("Homework2", true, "2022-08-30 13:13");
        assertEquals("[D][X] Homework2(by: Aug 30 2022 01:13 PM)", deadline2.toString());
    }

    @Test
    public void deadlineToStorageTest() {
        Deadline deadline = new Deadline("Homework", "2022-09-12 12:00");
        assertEquals("D|0|Homework|2022-09-12 12:00", deadline.toStorageString());

        Deadline deadline2 = new Deadline("Homework2", true, "2022-08-30 13:13");
        assertEquals("D|1|Homework2|2022-08-30 13:13", deadline2.toStorageString());
    }
}
