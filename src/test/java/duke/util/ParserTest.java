package duke.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void parseCommand_validCommand_success() {
        assertEquals("bye", new Parser().parseCommand("bye"));
        assertEquals("list", new Parser().parseCommand("list"));
        assertEquals("markTask", new Parser().parseCommand("mark 1234"));
        assertEquals("markTask", new Parser().parseCommand("mark123"));
        assertEquals("unMarkTask", new Parser().parseCommand("unmark"));
        assertEquals("unMarkTask", new Parser().parseCommand("unmark123"));
        assertEquals("deleteTask", new Parser().parseCommand("delete"));
        assertEquals("deleteTask", new Parser().parseCommand("delete123"));
        assertEquals("addToList", new Parser().parseCommand("todo abas"));
        assertEquals("addToList", new Parser().parseCommand("event abas /at 19/02/2022"));
        assertEquals("addToList", new Parser().parseCommand("deadline abas /by 10/03/2020"));
    }

    @Test
    public void parseTaskType_validCommand_success() {
        assertEquals("todoTask", new Parser().parseTaskType("todo abas"));
        assertEquals("eventTask", new Parser().parseTaskType("event abas /at 19/02/2022"));
        assertEquals("deadlineTask", new Parser().parseTaskType("deadline abas /by 10/03/2020"));
    }
}
