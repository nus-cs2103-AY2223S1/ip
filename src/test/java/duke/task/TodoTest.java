package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TodoTest {

    @Test
    public void testStringConversion() {
        assertEquals("[T][ ] Task1", new Todo("Task1").toString());
    }

}
