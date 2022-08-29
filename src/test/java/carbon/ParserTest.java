package carbon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import carbon.error.InvalidInputException;

public class ParserTest {
    private Ui ui = new Ui();
    private Storage storage = new Storage();
    private Parser parser = new Parser(this.ui, this.storage);

    @Test
    public void process_invalidCommand_returnsInvalidInputException() {
        String input = "testInvalidCommand";
        boolean hasError = false;
        try {
            this.parser.process(input);
        } catch (InvalidInputException error) {
            hasError = true;
        }
        assertTrue(hasError);
    }

    @Test
    public void process_bye_returnsEmptyLog() {
        String input = "bye";
        String expectedOutput = "";
        String actualOutput = this.parser.process(input);
        assertEquals(expectedOutput, actualOutput);
    }
}
