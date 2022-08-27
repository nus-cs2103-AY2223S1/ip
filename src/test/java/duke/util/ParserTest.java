package duke.util;

import duke.command.Command;
import duke.command.ErrorCommand;
import duke.command.UnknownCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    Parser testSubject = new Parser();

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
}
