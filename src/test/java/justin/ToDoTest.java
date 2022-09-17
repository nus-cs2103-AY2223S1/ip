package justin;

import justin.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void todoTest1() {
        assertEquals("T | Undone | Homework", new ToDo("Homework", false).toString());
    }

    @Test
    public void todoTest2() {
        ToDo task = new ToDo("Coding Assignment", false);
        task.mark();
        assertEquals("T | Done! | Coding Assignment", task.toString());
    }
}
