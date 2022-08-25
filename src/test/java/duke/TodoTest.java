package duke;

import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void toDo_correctStringFormat(){
        Todo todo = new Todo("Run the first test");
        todo.taskDone();
        assertEquals("[T][X] Run the first test", todo.toString());
    }

    @Test
    public void toDo_correctType(){
        Todo todo = new Todo("Run the second test");
        todo.taskDone();
        assertEquals("T", todo.getType());
    }
}