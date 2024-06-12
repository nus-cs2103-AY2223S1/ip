package duke.util;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.command.AddEventCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ErrorCommand;
import duke.command.UnknownCommand;

public class ParserTest {

    private final Parser testSubject = new Parser();

    @Test
    public void parse_unknownInstructionInput_unknownCommandOutput() {
        Command command = testSubject.parse("dfqwejifoasvaoief");
        if (!(command instanceof UnknownCommand)) {
            fail();
        }
    }

    @Test
    public void parse_unformattedInstructionInput_errorCommandOutput() {
        Command command = testSubject.parse("event orbital splashdown /by 2022-08-26 18:00:00");
        if (!(command instanceof ErrorCommand)) {
            fail();
        }
    }

    @Test
    public void parse_missingIndexInstructionInput_errorCommandOutput() {
        Command command = testSubject.parse("mark");
        if (!(command instanceof ErrorCommand)) {
            fail();
        }
    }

    @Test
    public void parse_missingTitleInstructionInput_errorCommandOutput() {
        Command command = testSubject.parse("event /at 2022-08-26 18:00:00");
        if (!(command instanceof ErrorCommand)) {
            fail();
        }
    }

    @Test
    public void parse_validAddEventInstructionInput_errorCommandOutput() {
        Command command = testSubject.parse("event orbital splashdown /at 2022-08-26 18:00:00");
        if (!(command instanceof AddEventCommand)) {
            fail();
        }
    }

    @Test
    public void parse_validDeleteInstructionInput_errorCommandOutput() {
        Command command = testSubject.parse("delete 1");
        if (!(command instanceof DeleteCommand)) {
            fail();
        }
    }
}
