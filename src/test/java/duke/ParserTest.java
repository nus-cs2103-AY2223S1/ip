package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void deleteCommand_invalidIndex_exceptionThrown() {
        try {
            Parser parser = new Chick().parser;
            parser.parse("delete loooool", false);
        } catch (ChickException e) {
            assertEquals(e.getMessage(), "Please input a valid number.");
        }
    }
}
