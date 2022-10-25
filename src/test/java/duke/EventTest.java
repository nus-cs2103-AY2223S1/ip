package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void toStringTest() throws DukeException {
        assertEquals("[E][ ] student life fair (at: Aug 14 2022)",
                new Event("student life fair", "2022-08-14").toString());
    }

    @Test
    public void toSaveTest() throws DukeException {
        assertEquals("E | 0 | student life fair | 2022-08-14\n",
                new Event("student life fair", "2022-08-14").toSave());
    }
}
