package betago.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;



public class TodoTest {

    @Test
    public void newTodoTest() {
        assertEquals("[T][ ] do this task", new Todo("do this task").toString());
    }

}

