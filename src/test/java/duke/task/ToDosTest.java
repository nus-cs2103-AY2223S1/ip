package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ToDosTest {

    @Test
    public void testToStringMethod() {
        assertEquals("[T][ ] todo task", new ToDos("todo task").toString());
    }

    @Test
    public void testToMemoryStringMethod() {
        assertEquals("T | 0 | todo task", new ToDos("todo task").toMemoryString());
    }
}