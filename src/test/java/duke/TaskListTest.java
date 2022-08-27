package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskType;
import duke.task.ToDo;



public class TaskListTest {

    private TaskList initializeTaskList() {
        TaskList list = new TaskList();
        Task task = new ToDo("", TaskType.TODO);
        for (int i = 0; i < 3; i++) {
            list.addTask(task);
        }
        return list;
    }
    @Test
    public void testListSize() {
        TaskList list = initializeTaskList();
        assertEquals(3, list.getListSize());
    }

    @Test
    public void markTask_exceptionThrown() {
        try {
            TaskList list = initializeTaskList();
            list.markTaskDone(3);
        } catch (DukeException e) {
            assertEquals("OOPS!!! I'm sorry, no tasks exist at this index :-(", e.getMessage());
        }
    }
}
