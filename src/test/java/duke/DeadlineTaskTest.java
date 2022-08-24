package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.DeadlineTask;

public class DeadlineTaskTest {

    @Test
    public void newDeadlineTest() {
        DeadlineTask deadline = new DeadlineTask("This is a test description", "2000-02-02");
        assertEquals(deadline.toString(), "[D][ ] This is a test description (by: Feb 2 2000)");
    }

    @Test
    public void markDeadlineTest() {
        DeadlineTask deadline = new DeadlineTask("This is a test description", "2000-02-02");
        deadline.markDone();
        assertEquals(deadline.toString(), "[D][X] This is a test description (by: Feb 2 2000)");
    }
}