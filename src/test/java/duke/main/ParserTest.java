package duke.main;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class ParserTest {
    @Test
    public void parse_missingDescription_exceptionThrown() {
        try {
            Parser parser = new Parser();
            parser.parse("todo");
        } catch (DukeException de) {
            assertEquals("Hmm... Seems like you did not add a task description or number!", de.getMessage());
        }
    }
}
