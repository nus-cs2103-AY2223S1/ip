package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

public class DukeTest {
    @Test
    public void addTodoToTaskList() {
        String descToDo = "Test todo for Duke !";
        String expected = "";
        String actual = "";
        try {
            TaskList taskList = new TaskList();
            Task todo = new ToDo(descToDo);
            expected = String.format("Got it. I've added this task:\n  %s\n%s\n",
                    todo, String.format("Now you have %d tasks in the list", 1));
            taskList.addTaskToList(todo);
            actual = taskList.getAddedTaskOutput(todo);
        } catch (Exception e) {
            actual = e.getMessage();
        }

        assertEquals(expected, actual);
    }
}
