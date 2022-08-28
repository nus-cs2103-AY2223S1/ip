package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void deleteCommand_invalidIndex_exceptionThrown() {
        try {
            Parser parser = new Duke().parser;
            parser.parse("delete loooool", false);
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "Please input a valid number.");
        }
    }
}
