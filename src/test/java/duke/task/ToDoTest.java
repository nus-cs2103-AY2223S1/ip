package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void testMarkDone_executeMarkDone_deadlineIsDone() {
        assertTrue(new ToDo("dummy").markDone().isDone());
    }

    @Test
    public void testUnmarkDone_executeUnmarkDone_deadlineIsNotDone() {
        assertFalse(new ToDo("dummy").unmarkDone().isDone());
    }

    @Test
    public void testToString_executeToString_outputStringIsCorrect() {
        assertEquals(new ToDo("dummy").toString(),
                "[T][ ] dummy");
    }
}
