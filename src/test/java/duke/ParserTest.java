package duke;

import duke.command.AddCommand;
import duke.command.Command;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void testParser() {
        Parser parser = new Parser();

        Command command = parser.parse("deadline cs2103 iP!! /by 2020-12-12 1233");
        Command addCommand = new AddCommand("deadline cs2103 iP!! /by 2020-12-12 1233");

        assertEquals(command.isExit(), addCommand.isExit());
    }
}
