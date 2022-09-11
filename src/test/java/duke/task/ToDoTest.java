package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
