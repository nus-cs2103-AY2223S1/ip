package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TodoTest {
    @Test
    public void getTaskTest() {
        Todo todo = new Todo("hello");


        assertEquals("[T] [ ] hello", todo.getTask());
    }
}