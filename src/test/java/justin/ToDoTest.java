package justin;

import justin.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void dummyTest1() {
        assertEquals("T | O | Homework", new ToDo("Homework", false).toString());
    }

    @Test
    public void dummyTest2() {
        ToDo task = new ToDo("Coding Assignment", false);
        task.mark();
        assertEquals("T | X | Coding Assignment", task.toString());
    }
}
