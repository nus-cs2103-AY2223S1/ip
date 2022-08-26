package duke.task;

import org.junit.jupiter.api.Test;

import duke.exceptions.CorruptedLineException;
import duke.util.ParsedData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TodoTest {
    @Test
    public void toStringTest() {
        try {
            Todo dummy = Todo.createTodo("dummy test123 /by 12-3-1232");
            assertEquals("[T][ ] dummy test123 /by 12-3-1232", dummy.toString());
            dummy.mark();
            assertEquals("[T][X] dummy test123 /by 12-3-1232", dummy.toString());
            dummy.unmark();
            assertEquals("[T][ ] dummy test123 /by 12-3-1232", dummy.toString());
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

    @Test
    public void convertToParseDataTest() {
        try {
            Todo dummy = Todo.createTodo("dummy test123");
            ParsedData result = dummy.convertToParseData();
            assertEquals("Tx", result.command);
            assertEquals("dummy test123", result.description);
            assertEquals("", result.additionalInfo);
            dummy.mark();
            result = dummy.convertToParseData();
            assertEquals("Tc", result.command);
            assertEquals("dummy test123", result.description);
            assertEquals("", result.additionalInfo);
        } catch (CorruptedLineException e) {
            fail();
        }
    }
}
