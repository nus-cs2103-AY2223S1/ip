package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTaskTest {

    @Test
    public void createTodoTaskTest() {
        TodoTask todoTask = new TodoTask("work");
        assertEquals("[T][ ] work", todoTask.toString());
    }
}
