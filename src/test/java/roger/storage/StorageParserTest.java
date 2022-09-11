package roger.storage;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import roger.tasks.Event;
import roger.tasks.ToDo;


public class StorageParserTest {
    @Test
    public void toTask_validEventString_eventReturned() {
        assertTrue(StorageParser.toTask("E | 1 | Party at Marina Bay Sands | 2022-10-22") instanceof Event);
    }

    @Test
    public void toTask_validToDoString_toDoReturned() {
        assertTrue(StorageParser.toTask("T | 1 | Party at Marina Bay Sands") instanceof ToDo);
    }

    @Test
    public void toTask_invalidTaskString_exceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> StorageParser.toTask("foo bar"));
    }
}
