package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DukeTest {
    @Test
    public void dukeTodoTest() {
        assertEquals("[T][N] ruaAshy", new Todo("ruaAshy").toString());
    }

    @Test
    public void dukeEventTest() {
        assertEquals("E|N|ruaPutu|2022-10-01", new Event("ruaPutu", "2022-10-01").writeToFile());
    }
}