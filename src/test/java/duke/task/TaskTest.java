package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class TaskTest {
    private final Task unfinishedToDo = new ToDo("Submit iP week 3");
    private final Task finishedToDo = new ToDo("Do tutorial week 3");

    public TaskTest() throws DukeException {

    }

    @Test
    public void getStatusIconTest() {
        assertEquals(" ", unfinishedToDo.getStatusIcon());
        finishedToDo.markTaskAsDone();
        assertEquals("X", finishedToDo.getStatusIcon());
    }

}
