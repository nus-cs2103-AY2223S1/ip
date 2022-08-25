package duke.main;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Test class for Parser class.
 */
public class ParserTest {

    /**
     * Tests parse method with invalid input.
     * Exception should be thrown with error message equal to the expected output.
     */
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
