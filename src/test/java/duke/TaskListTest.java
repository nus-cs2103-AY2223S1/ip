package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class TaskListTest {
    @Test
    public void add_deadline_deadlineAddedWithDate() {
        TaskList t = new TaskList();
        t.addDeadline("deadline test /by 2022-12-12");
        assertEquals("1.[D][ ] test 12 DECEMBER 2022\n", t.toString());
    }
}
