package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void toStringTest() {
        ToDo t = new ToDo("Watch Veritasium!");
        assertEquals(t.toString(), "[T][ ] Watch Veritasium!");
    }
}
