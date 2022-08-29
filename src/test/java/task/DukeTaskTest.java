package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DukeTaskTest {

    @Test
    void testToString() {
        assertEquals("[T][ ] buy bread", new DukeTask("buy bread", false, 'T').toString());
    }

    @Test
    void testToStringSaveFile() {
        assertEquals("T/O/buy bread", new DukeTask("buy bread", false, 'T').toStringSaveFile());
    }
}