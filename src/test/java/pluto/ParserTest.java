package pluto;

import org.junit.jupiter.api.Test;
import pluto.command.AddCommand;
import pluto.task.Event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parseTask_invalidDateFormat_exceptionThrown() {
        try {
            Parser.parseTask("join meeting /by 04/05/2022", Parser.Type.DEADLINE);
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("\tOOPS!!! dd-MM-yyyy HHmm date format required.", e.getMessage());
        }
    }

    @Test
    public void parseTask_validFormat_success() throws PlutoException {
        LocalDateTime date = LocalDateTime.parse("04-05-2022 1800", DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
        assertEquals(new AddCommand(new Event("join meeting", date)),
                Parser.parseTask("join meeting /at 04-05-2022 1800", Parser.Type.EVENT));
    }

    @Test
    public void isOnlyCommand_onlyCommand_exceptionThrown() {
        try {
            Parser.isOnlyCommand("todo");
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("\tOOPS!!! The description of todo cannot be empty.", e.getMessage());
        }
    }
}