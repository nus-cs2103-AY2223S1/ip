package duke;

import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void testAddTask() {
        ArrayList<Task> tasks = new ArrayList<>();
        TaskList taskList = new TaskList(tasks);
        Todo todo = new Todo("todo");
        taskList.addTask(todo);
        Deadline deadline = new Deadline("deadline", "02/09/2022 13:40");
        taskList.addTask(deadline);
        Event event = new Event("event", "02/09/2022 13:40");
        taskList.addTask(event);
        ArrayList<Task> testList = new ArrayList<>();
        testList.add(0, todo);
        testList.add(1, deadline);
        testList.add(2, event);
        assertEquals(testList, taskList.getList());
    }
}
