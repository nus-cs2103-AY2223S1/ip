package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class TaskListTest {
    @Test
    public void addToTaskList_correct() {
        Duke duke = new Duke();
        duke.parseCommand("deadline return book /by 2022-08-28");
        assertEquals(duke.tasks.getSize(), 1);
    }

    @Test
    public void markTask_correct() {
        Duke duke = new Duke();
        duke.parseCommand("deadline return book /by 2022-08-28");
        duke.parseCommand("mark 1");
        assertEquals(duke.tasks.tasks.get(0).getStatus(), "mark");
        duke.parseCommand("unmark 1");
        assertEquals(duke.tasks.tasks.get(0).getStatus(), "unmark");
    }
}
