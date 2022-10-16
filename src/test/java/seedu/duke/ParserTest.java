package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void parseTest() {
        String input = "deadline /by 2022-07-16 1800";
        Parser parser = new Parser();
        Command command = parser.parse(input);

        assertEquals(new DeadlineCommand(), command);
    }
}
