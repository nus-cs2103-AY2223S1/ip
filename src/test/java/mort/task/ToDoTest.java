package mort.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ToDoTest {
    private final ToDo todo1 = new ToDo("buy apples", true);
    private final ToDo todo2 = new ToDo("buy 6 apples & chicken", false);
    private final LocalDate date = LocalDate.parse("31/12/1926", DateTimeFormatter.ofPattern("d/M/yyyy"));
    
    @Test
    public void testGetStatusIcon() {
        assertEquals("X", todo1.getStatusIcon());
        assertEquals(" ", todo2.getStatusIcon());
    }
    @Test
    public void testGetSaveFormat() {
        assertEquals("T | 1 | buy apples", todo1.getSaveFormat());
        assertEquals("T | 0 | buy 6 apples & chicken", todo2.getSaveFormat());
    }

    @Test
    public void testToString() {
        assertEquals("[T][X] buy apples", todo1.toString());
        assertEquals("[T][ ] buy 6 apples & chicken", todo2.toString());
    }
    @Test
    public void testIsMatch() {
        assertTrue(todo2.isMatch("appl"));
        assertTrue(todo2.isMatch("6 APpl"));
        assertTrue(todo2.isMatch("buy 6 Appl"));
        assertTrue(todo2.isMatch("appl"));
        assertFalse(todo2.isMatch("[X]"));
        assertFalse(todo2.isMatch("homework"));
        assertFalse(todo1.isMatch("[ ]"));
    }
    
    @Test
    public void testIsDateMatch() {
        assertFalse(todo1.isDateMatch(date));
        assertFalse(todo2.isDateMatch(date));
    }
}
