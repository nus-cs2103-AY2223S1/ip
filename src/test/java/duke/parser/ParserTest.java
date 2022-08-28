package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class ParserTest {
    private final Parser sampleParser = new Parser();

    @Test
    public void parseText_inputEqualsBye_isListeningBecomeFalse() {
        try {
            sampleParser.parseText("bye");
            assertEquals(sampleParser.getIsListening(), false);
        } catch (DukeException error) {
            fail("Should not have thrown any error");
        }
    }

    @Test
    public void parseText_validInput_returnSuccessfully() {
        try {
            sampleParser.parseText("mark 1");
            return;
        } catch (DukeException error) {
            fail("Should not have thrown any error");
        }
    }

    @Test
    public void parseText_invalidInput_throwError() {
        try {
            sampleParser.parseText("get all tasks");
            fail("Should have thrown DukeException");
        } catch (DukeException error) {
            return;
        }
    }
}
