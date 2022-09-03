package duke;

import duke.command.AddCommand;
import duke.command.DeleteCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parse_incompleteInput_exceptionThrown() {
        try {
            assertEquals(new AddCommand("deadline", " "), Parser.parse("deadline"));
            fail();
        } catch (DukeException e) {
            assertEquals("oops, your command seems to be incomplete :(", e.getMessage());
        }
    }

    @Test
    public void parse_deleteAsInput_newDeleteCommandCreated() {
        try {
            assertEquals(true, Parser.parse("delete 2") instanceof DeleteCommand);
        } catch (DukeException e) {
            fail();
        }

    }
}
