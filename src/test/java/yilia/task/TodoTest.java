package yilia.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TodoTest {
    private final Todo todo1 = new Todo("go to the park", true);
    private final Todo todo2 = new Todo("return a book");

    @Test
    public void toString_normalInput_writtenCorrectly() {
        assertEquals(todo1.toString(), "[T][X] go to the park");
        assertEquals(todo2.toString(), "[T][ ] return a book");
    }

    @Test
    public void status_check_statusCorrect() {
        todo1.setDone();
        todo2.setDone();
        assertTrue(todo1.status());
        assertTrue(todo2.status());
    }

    @Test
    public void status_uncheck_statusCorrect() {
        todo1.setNotDone();
        todo2.setNotDone();
        assertFalse(todo1.status());
        assertFalse(todo2.status());
    }

    @Test
    public void parse_normalInput_writtenCorrectly() {
        assertEquals(todo1.parse(), "T / 1 / go to the park");
        assertEquals(todo2.parse(), "T / 0 / return a book");
    }
}
