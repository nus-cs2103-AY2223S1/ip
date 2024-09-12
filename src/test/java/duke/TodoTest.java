package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TodoTest {
    Todo testTodo = new Todo("test");

    @Test
    public void todoTestDone(){
        assertFalse(testTodo.getStatus());
    }

    @Test
    public void todoTestSetDone(){
        testTodo.setDone();
        assertTrue(testTodo.getStatus());
    }
}
