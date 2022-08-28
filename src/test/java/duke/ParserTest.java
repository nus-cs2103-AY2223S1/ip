package duke;

import duke.command.ExitCommand;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void testParse_byeCommand_success() {
        try {
            assertEquals(true, Parser.parse("bye").isExit());
        } catch (DukeException e) {
            fail();
        }
    }

}
