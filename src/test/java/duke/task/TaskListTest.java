package duke.task;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void markTheTaskTest() throws DukeException {
        boolean expected = true;
        Task task = new Deadline("homework", "12/12/2022 1800", false);
        ArrayList<Task> ls = new ArrayList<>();
        ls.add(task);
        TaskList taskList = new TaskList(ls);
        taskList.markTheTask(1);
        boolean actual = taskList.getTask(1).isDone;
        assertEquals(expected, actual);
    }
}