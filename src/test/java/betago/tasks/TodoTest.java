package betago.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void newTodoTest(){
        assertEquals("[T][ ] do this task", new Todo("do this task").toString());
    }

}

