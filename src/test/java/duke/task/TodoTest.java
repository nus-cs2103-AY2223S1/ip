package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void toStringTest() {
        Todo sampleTodo = new Todo("Perform automated testing");
        assertEquals("[T][ ] Perform automated testing", sampleTodo.toString());
    }
}