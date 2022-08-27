package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ToDoTest {

    @Test
    void testToString() {
        ToDo t = new ToDo("homework");
        assertEquals("[T][ ] homework", t.toString());
    }

    @Test
    void taskMemo() {
        ToDo t = new ToDo("homework");
        assertEquals("T | 0 | homework", t.taskMemo());
    }
}