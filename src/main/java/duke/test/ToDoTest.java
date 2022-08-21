package duke.test;

import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToDoTest {

    @Test
    void testToString() {
        assertEquals("[T][ ] buy bread", new ToDo("buy bread").toString());
    }

    @Test
    void toStringData() {
        assertEquals("T | 0 | buy bread", new ToDo("buy bread").toStringData());
    }
}