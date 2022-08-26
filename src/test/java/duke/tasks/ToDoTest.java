package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ToDoTest {

    @Test
    void testToString() {
        assertEquals("[T][ ] test1",
                new ToDo("test1", false).toString());
        assertEquals("[T][X] test2",
                new ToDo("test2", true).toString());
    }

    @Test
    void testTaskToDataString() {
        ToDo test3 = new ToDo("test3", false);
        assertEquals("T | X | test3\n", test3.taskToDataString());
        ToDo test4 = new ToDo("test4", true);
        assertEquals("T | O | test4\n", test4.taskToDataString());
    }
}