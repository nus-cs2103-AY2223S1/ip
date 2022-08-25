package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void toStringTest() {
        Todo task = new Todo("testing");
        assertEquals("[T][ ] testing", task.toString());
    }

}
