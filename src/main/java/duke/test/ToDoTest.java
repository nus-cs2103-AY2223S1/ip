package duke.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.ToDo;



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
