package task;


import org.junit.jupiter.api.Test;
import duke.task.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void toStringTest(){
        assertEquals("[T][ ] testing", new Todo("testing").toString());
    }
}
