package duke;

import duke.component.TaskList;
import duke.exception.DukeException;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class TaskListTest {

    @Test
    public void addTask_newToDo_success() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("test1"));
        assertEquals("1. [T][ ] test1", taskList.toString());
    }

    @Test
    public void deleteTask_wrongIndex_exceptionThrown() {
        TaskList taskList = new TaskList();
        try {
            taskList.deleteTask(1);
            assertEquals("1. [T][ ] test1", taskList.toString());
            fail();
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! There is no such task number!", e.getMessage());
        }
    }
}
