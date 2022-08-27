package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void getAsStringArray_makingObjects_normalBehavior() {
        Task task1 = new ToDo("task one", false);
        Task task2 = new ToDo("task two", true);
        Task task3 = new ToDo("task three");

        assertEquals("[T][ ] task one", task1.toString());
        assertEquals("[T][X] task two", task2.toString());
        assertEquals("[T][ ] task three", task3.toString());

        assertArrayEquals(new String[] { "ToDo", "task one", "false" }, task1.getAsStringArray());
        assertArrayEquals(new String[] { "ToDo", "task two", "true" }, task2.getAsStringArray());
        assertArrayEquals(new String[] { "ToDo", "task three", "false" }, task3.getAsStringArray());
    }
}
