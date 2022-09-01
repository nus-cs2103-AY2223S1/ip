package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void testToStringConversion() {
        assertEquals("[T][ ] testing", new ToDo("testing").toString());
        assertEquals("[T][ ] multiple words", new ToDo("multiple words").toString());
        assertEquals("[T][X] marked", new ToDo("marked", true).toString());

    }

    @Test
    public void testToStorageStringConversion() {
        assertEquals("T|0|testing", new ToDo("testing").toStorageString());
        assertEquals("T|0|multiple words", new ToDo("multiple words").toStorageString());
        assertEquals("T|1|marked", new ToDo("marked", true).toStorageString());
    }
}
