package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class TaskListTest {

    @Test
    public void markTheTaskTest() throws DukeException {
        boolean isExpected = true;
        Task task = new Deadline("homework", "12/12/2022 1800", false);
        ArrayList<Task> ls = new ArrayList<>();
        ls.add(task);
        TaskList taskList = new TaskList(ls);
        taskList.markTheTask(1);
        boolean actual = taskList.getTask(1).isDone;
        assertEquals(isExpected, actual);
    }
}
