package duke.task;

import duke.logic.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void toStringTest(){
        assertEquals("[T][ ]  a", new ToDo("a").toString());
    }
}
