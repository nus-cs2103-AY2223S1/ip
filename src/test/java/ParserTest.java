import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.Parser;
import duke.command.Command;
import duke.command.ExitCommand;
import duke.command.ListCommand;

public class ParserTest {
    @Test
    @DisplayName("Check parse input for bye and list")
    public void checkParseInputByeAndList() {
        Command c1 = null;
        Command c2 = null;
        try {
            c1 = Parser.parseInput("bye");
            c2 = Parser.parseInput("list");

        } catch (DukeException e) {
            fail(e.getMessage());
        }
        assertEquals(ExitCommand.of(), c1);
        assertEquals(ListCommand.of(), c2);
    }
}
