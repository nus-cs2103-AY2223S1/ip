package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void test() {
        Task todo = new Todo("hi");
        assertEquals(todo.isDone, false);

        todo.mark();
        assertEquals(todo.isDone, true);
    }
}
