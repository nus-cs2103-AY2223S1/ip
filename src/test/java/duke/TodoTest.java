package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void newTodoCreated(){
        Todo todo = new Todo("do this");
        assertEquals("[T][ ] do this", todo.toString());
    }
}
