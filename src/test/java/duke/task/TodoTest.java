package duke.task;

import org.junit.jupiter.api.Test;

import duke.exceptions.CorruptedLineException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TodoTest {
    @Test
    public void toStringTest() {
        try {
            Todo dummy = Todo.createTodo("dummy test123");
            assertEquals("[T][ ] dummy test123", dummy.toString());
            dummy.mark();
            assertEquals("[T][X] dummy test123", dummy.toString());
            dummy.unmark();
            assertEquals("[T][ ] dummy test123", dummy.toString());
        } catch (CorruptedLineException e) {
            fail();
        }
    }

    @Test
    public void markTest() {
        try {
            Todo dummy = Todo.createTodo("dummy test123");
            assertEquals(false, dummy.completed);
            dummy.mark();
            assertEquals(true, dummy.completed);
            dummy.mark();
            assertEquals(true, dummy.completed);
            dummy.unmark();
            assertEquals(false, dummy.completed);
            dummy.unmark();
            assertEquals(false, dummy.completed);
        } catch (CorruptedLineException e) {
            fail();
        }
    }
}
