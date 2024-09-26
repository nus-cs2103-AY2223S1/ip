package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class DeadlineTaskTest {

    @Test
    public void createDeadlineTaskTest() throws DukeException {
        DeadlineTask deadlineTask = new DeadlineTask("work ", "2022-12-20 1800");
        assertEquals("[D][ ] work  (by: Dec 20 2022 6:00 PM)", deadlineTask.toString());
    }
}
