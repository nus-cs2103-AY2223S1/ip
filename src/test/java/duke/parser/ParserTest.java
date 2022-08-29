package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.command.ExitCommand;
import duke.exception.DukeException;

public class ParserTest {

    @Test
    public void byeCommandTest() {
        try {
            assertEquals(true, Parser.parse("bye").isExit());
            assertEquals(true, Parser.parse("bye bye").isExit());
            assertTrue(Parser.parse("bye") instanceof ExitCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void deadlineCommandTest() {
        try {
            assertEquals(false, Parser.parse("deadline return book /by 2/12/2019 1800").isExit());
        } catch (DukeException e) {
            fail();
        }
        try {
            Parser.parse("deadline");
        } catch (DukeException e) {
            assertEquals(e.toString(), "OOPS!!! The description of a Deadline cannot be empty.");
        }
        try {
            Parser.parse("deadline return book /by 2 jan 2020 1800");
        } catch (DukeException e) {
            assertEquals(e.toString(), "OOPS!!! The description of the Date is invalid!\n"
                    + "Please input the date in d/MM/yyyy format e.g. 2/12/2019 1800.");
        }
    }

}
