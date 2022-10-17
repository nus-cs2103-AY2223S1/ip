package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;

public class TaskListTest {

    @Test
    public void printTaskList_nonEmptyList() {
        TaskList t = new TaskList(new ArrayList<Task>());
        t.addTask(new ToDo("Hello world"));
        assertEquals("Here are the task(s) in your list:\n" + "  1. [T][ ] Hello world",
                t.taskListString());
    }

    @Test
    public void printTaskList_emptyList() {
        TaskList t = new TaskList(new ArrayList<Task>());
        assertEquals("You currently have no tasks",
                t.taskListString());
    }


}