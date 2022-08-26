package duke.main;

import duke.command.*;
import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    void parserTest() throws DukeException {
        assertTrue(Parser.parse("bye") instanceof ExitCommand);
        assertTrue(Parser.parse("list") instanceof ListCommand);
        assertTrue(Parser.parse("mark 2") instanceof DoneCommand);
        assertTrue(Parser.parse("delete 2") instanceof DeleteCommand);

        assertTrue(Parser.parse("todo todo") instanceof AddTodoCommand);
        assertTrue(Parser.parse("deadline deadlineTest /by deadlineBy") instanceof AddDeadlineCommand);
        assertTrue(Parser.parse("deadline deadlineTest /by 25/07/2015 1500") instanceof AddDeadlineCommand);
        assertTrue(Parser.parse("event eventTest /at eventAt") instanceof AddEventCommand);
        assertTrue(Parser.parse("event eventTest /at 26/07/2014 1600") instanceof AddEventCommand);
    }
}
