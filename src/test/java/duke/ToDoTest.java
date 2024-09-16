package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.tasks.ToDo;

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
