package duke.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void isValidTask_validCommand_success() {
        assertEquals(true, new Parser().isValidTask("todoTask"));
        assertEquals(true, new Parser().isValidTask("eventTask"));
        assertEquals(true, new Parser().isValidTask("deadlineTask"));
    }

    @Test
    public void isValidTask_invalidCommand_fail() {
        assertEquals(false, new Parser().isValidTask("hbchdbchdc"));
        assertEquals(false, new Parser().isValidTask(""));
        assertEquals(false, new Parser().isValidTask("hi"));
        assertEquals(false, new Parser().isValidTask("1767162"));
        assertEquals(false, new Parser().isValidTask("[[][]]]]"));
    }

    @Test
    public void parseTaskType_validCommand_success() {
        assertEquals("todoTask", new Parser().parseTaskType("todo abas"));
        assertEquals("eventTask", new Parser().parseTaskType("event abas /at 19/02/2022"));
        assertEquals("deadlineTask", new Parser().parseTaskType("deadline abas /by 10/03/2020"));
    }
}
