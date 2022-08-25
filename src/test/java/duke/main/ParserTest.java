package duke.main;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parse_missingArgument_exceptionThrown() {
        try {
            Parser parser = new Parser();
            parser.parse("todo");
            fail();
        } catch (DukeException de) {
            assertEquals("\tHey! Did you forget to add a task name / number?", de.getMessage());
        }
    }
}
