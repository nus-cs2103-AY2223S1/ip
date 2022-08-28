package duke;

import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parseTest(){
        try {
            assertTrue(Parser.parse("bye") instanceof ExitCommand);
            assertTrue(Parser.parse("event /at 22-2-2022 18:50")
                    instanceof EventCommand);
            assertTrue(Parser.parse("mark 1") instanceof MarkCommand);
            assertTrue(Parser.parse("unmark 2") instanceof UnmarkCommand);
        } catch (Exception e) {
            fail("Exception caused " + e.getMessage());
        }

    }

}
