package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;

public class DeadlineTest {

    @Test
    public void newDeadlineTest() {
        Deadline deadline = new Deadline("This is a test description", "2000-02-02");
        assertEquals(deadline.toString(), "[D][ ] This is a test description (by: February 2, 2000)");
    }

    @Test
    public void markDeadlineTest() {
        Deadline deadline = new Deadline("This is a test description", "2000-02-02");
        deadline.mark();
        assertEquals(deadline.toString(), "[D][X] This is a test description (by: February 2, 2000)");
    }
}
