package duke.task;

import duke.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
