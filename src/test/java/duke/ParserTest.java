package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.command.AddCommand;
import duke.command.Command;
import duke.exception.DukeException;
public class ParserTest {

    @Test
    public void testParser() throws DukeException {
        Parser parser = new Parser();

        Command command = parser.parse("deadline cs2103 iP!! /by 2020-12-12 1233");
        Command addCommand = new AddCommand("deadline cs2103 iP!! /by 2020-12-12 1233");

        assertEquals(command.isExit(), addCommand.isExit());
    }
}
