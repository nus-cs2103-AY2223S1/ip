package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    public void dummyTest() {
        assertNotEquals(3,4);
    }

    @Test
    public void parser_parseDateTime_exceptionThrown() {
        Parser testParser = new Parser();
        String badDateTime = "41-05-2022-22-24";
        assertThrows(IllegalDateTimeException.class,
                () -> testParser.parseDateTime(badDateTime));
    }

    @Test
    public void parser_parseCommand_exceptionNotThrown() {
        Parser testParser = new Parser();
        String[] testCommandArgs = new String[]{"event",
                "test /at 15-03-2019-21-01"};
        assertAll(() -> testParser.parseCommand(testCommandArgs));
    }
}
