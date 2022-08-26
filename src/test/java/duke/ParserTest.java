package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class handles the logic of the Parser tester.
 */
public class ParserTest {

    /**
     * Uses parseInput method with a valid input.
     * Output should be equal to the expected command.
     */
    @Test
    public void parseInput_correctInput_commandOutput() {
        Parser.parseInput("todo test 1");
        assertEquals(Parser.getUserCommand(), Command.TODO);
    }
}

