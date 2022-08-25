package duke.task;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    private Task unfinishedToDo = new ToDo("Submit iP week 3");
    private Task finishedToDo = new ToDo("Do tutorial week 3");

    public TaskTest() throws DukeException {

    }

    @Test
    public void getStatusIconTest() {
        assertEquals(" ", unfinishedToDo.getStatusIcon());
        finishedToDo.markTaskAsDone();
        assertEquals("X", finishedToDo.getStatusIcon());
    }

}
