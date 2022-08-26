package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void newDeadlineTest() {
        Deadline deadline = new Deadline("This is a test description", "01-10-2000 1800");
        assertEquals(deadline.toString(), "[D][ ] This is a test description (by: Sunday, 1 October 2000 6:00 PM)");
    }

    @Test
    public void markDeadlineTest() {
        Deadline deadline = new Deadline("This is a test description", "01-10-2000 1800");
        deadline.mark();
        assertEquals(deadline.toString(), "[D][X] This is a test description (by: Sunday, 1 October 2000 6:00 PM)");
    }
}
