package seedu.duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseTest() {
        String input = "deadline /by 2022-07-16 1800";
        Parser parser = new Parser();
        Command command = parser.parse(input);

        assertEquals(Command.DEADLINE, command);
    }
}
