package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.task.Task;

public class ParserTest {
    @Test
    public void fromStorage_deadlineTask() {
        Task t = Parser.fromStorage("D|1|foo bar|2022-04-20");
        assertEquals("[D][X] foo bar (by: Apr 20 2022)", t.toString());
    }

    @Test
    public void parseCommand_invalidMarkTaskIndex_exceptionThrown() {
        try {
            Parser.parseCommand("mark foo");
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid task indices: foo", e.getMessage());
        }
    }
}
