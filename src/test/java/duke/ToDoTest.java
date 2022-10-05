package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    void saveTaskAsString_normalInput_writtenCorrectly() {
        assertEquals("T | 0 | homework", new ToDo("homework").saveTaskAsString());
    }

    @Test
    void toString_normalInput_writtenCorrectly() {
        assertEquals("[T][ ] homework", new ToDo("homework").toString());
    }
}
