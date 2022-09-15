package duke.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.ToDo;



class ToDoTest {

    @Test
    void toString_todoTask() {
        assertEquals("[T][ ] buy bread", new ToDo("buy bread").toString());
    }

    @Test
    void toStringStorage_todoTask() {
        assertEquals("T | 0 | buy bread", new ToDo("buy bread").toStringStorage());
    }
}
