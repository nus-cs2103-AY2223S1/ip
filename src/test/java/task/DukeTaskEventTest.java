package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DukeTaskEventTest {

    @Test
    void testToString() {
        assertEquals("[E][ ] sleep(noon)", new DukeTaskEvent("sleep", false, 'E', "(noon)").toString());
    }

    @Test
    void testToStringSaveFile() {
        assertEquals("E/O/sleep/(noon)", new DukeTaskEvent("sleep", false, 'E', "(noon)").toStringSaveFile());
    }
}