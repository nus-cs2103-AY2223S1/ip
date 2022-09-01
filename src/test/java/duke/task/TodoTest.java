package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void testMarkTask() {
        ToDo t = new ToDo("Todo");
        t = t.markTask();
        assertTrue(t.isDone);
    }

    @Test
    public void testUnmarkTask() {
        ToDo t = new ToDo("Todo");
        t = t.unmarkTask();
        assertFalse(t.isDone);
    }

    @Test
    public void testToString() {
        ToDo t = new ToDo("Todo");
        assertEquals("[T][ ] Todo", t.toString());
    }
    
}
