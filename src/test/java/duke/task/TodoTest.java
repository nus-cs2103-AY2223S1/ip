package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;



public class TodoTest {
    @Test
    public void getTaskTest() {
        Todo todo = new Todo("hello");


        assertEquals("[T] [ ] hello", todo.getTask());
    }
}
