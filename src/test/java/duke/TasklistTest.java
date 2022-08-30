package duke;

import duke.task.Task;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void addTest() throws DukeException {
        Todo todo = new Todo("feed cat");
        TaskList taskList = new TaskList();
        taskList.addTask(todo);
        assertEquals("[T][ ] feed cat", taskList.getTaskAtIndex(1).toString());
    }

    @Test
    public void deleteTest() throws DukeException {
        Todo todo = new Todo("feed cat");
        TaskList taskList = new TaskList();
        taskList.addTask(todo);
        assertEquals(1, taskList.getTaskListSize());
        taskList.deleteTask(1);
        assertEquals(0, taskList.getTaskListSize());
    }
}