package roger;

import org.junit.jupiter.api.Test;
import roger.storage.StorageParser;
import roger.tasks.Event;
import roger.tasks.ToDo;

import static org.junit.jupiter.api.Assertions.*;

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
    public void toTask_invalidTaskString_ExceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> StorageParser.toTask("foo bar"));
    }
}
