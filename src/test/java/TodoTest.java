package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.DukeException;

public class TodoTest {

    @Test
    public void test() throws DukeException {
        Todo task = new Todo("testing");
        task.doing();
        assertEquals("[T][X] testing", task.toString());

        assertEquals(true, task.isDone());

        task.undo();
        assertEquals("[T][ ] testing", task.toString());

        assertEquals("testing", task.getDescription());

        assertEquals(false, task.isDone());
    }
}
