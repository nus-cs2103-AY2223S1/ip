import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import jarvis.task.Todo;

public class TodoTest {
    @Test
    public void markUndoneTest() {
        Todo todo = new Todo("toodoo", true);
        assertEquals("[T][X] toodoo", todo.toString());
    }
}
