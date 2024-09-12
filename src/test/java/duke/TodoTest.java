package duke;

import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void unmarkedTest(){
        assertEquals(new Todo("123456").toString(), "[T][ ] 123456");
    }

    @Test
    public void markedTest(){
        Todo temp = new Todo("123456");
        temp.markAsDone();
        assertEquals(temp.toString(), "[T][X] 123456");
    }
}