package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void toStringTest() throws DukeException {
        assertEquals("[T][ ] return books",
                new Todo("return books").toString());
    }

    @Test
    public void toSaveTest() throws DukeException {
        assertEquals("T | 0 | return books\n",
                new Todo("return books").toSave());
    }
}
