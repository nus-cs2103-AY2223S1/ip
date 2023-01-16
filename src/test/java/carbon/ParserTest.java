package carbon;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import carbon.error.InvalidInputException;
import carbon.error.InvalidParamException;

public class ParserTest {
    private Ui ui = new Ui();
    private Storage storage = new Storage();
    private Parser parser = new Parser(this.ui, this.storage);

    @Test
    public void process_invalidCommand_returnsInvalidInputException() {
        String input = "testInvalidCommand";
        boolean hasError = false;
        try {
            this.parser.processCommand(input);
        } catch (InvalidInputException error) {
            hasError = true;
        }
        assertTrue(hasError);
    }

    @Test
    public void process_outOfBoundsIndex_returnsInvalidParamException() {
        String input = "mark -1";
        boolean hasError = false;
        try {
            this.parser.processCommand(input);
        } catch (InvalidParamException error) {
            hasError = true;
        }
        assertTrue(hasError);
    }
}
