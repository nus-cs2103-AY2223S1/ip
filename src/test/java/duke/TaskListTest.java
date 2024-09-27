package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.task.Priority;
import duke.task.TaskList;
import duke.task.TaskType;
import duke.task.ToDo;

public class TaskListTest {

    private TaskList initializeTaskList() {
        TaskList list = new TaskList();
        ToDo task = new ToDo("", TaskType.TODO);
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
            assertEquals("Unfortunate, no tasks exist at this index", e.getMessage());
        }
    }

    @Test
    public void deleteTask_exceptionThrown() {
        try {
            TaskList list = initializeTaskList();
            list.deleteTask(3);
        } catch (DukeException e) {
            assertEquals("Unfortunate, no tasks exist at this index", e.getMessage());
        }
    }

    @Test
    public void markTaskSuccess() {
        TaskList list = initializeTaskList();
        list.markTaskDone(2);
        assertEquals(1, list.retrieveTask(2).getDoneStatus());
    }

    @Test
    public void changeTaskPrioritySuccess() {
        TaskList list = initializeTaskList();
        list.setTaskPriority(2, Priority.HIGH);
        assertEquals(Priority.HIGH, list.retrieveTask(2).getPriority());
    }
}
