package duke.task;

import org.junit.jupiter.api.Test;
import task.ToDo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void addToDoTest() {
        ToDo todo = new ToDo("add todo 1");
        assertEquals("[T][ ] add todo 1", todo.toString());
    }

    @Test
    public void saveToDoTest() {
        ToDo todo = new ToDo("save todo 2");
        assertEquals("[T][ ] save todo 2", todo.toString());
    }
}
