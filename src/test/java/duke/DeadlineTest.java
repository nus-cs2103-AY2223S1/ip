package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void toStringTest() throws DukeException {
        assertEquals("[D][ ] return books (by: Dec 31 2022)",
                new Deadline("return books", "2022-12-31").toString());
    }

    @Test
    public void toSaveTest() throws DukeException {
        assertEquals("D | 0 | return books | 2022-12-31\n",
                new Deadline("return books", "2022-12-31").toSave());
    }
}
