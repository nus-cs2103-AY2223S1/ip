import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.RequestHandler;
import duke.exception.IncompleteInputException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidInputException;


public class RequestHandlerTest {

    @Test
    public void handleRequest_unknownCommand_exceptionThrow() throws InvalidCommandException, IncompleteInputException,
            InvalidInputException {
        try {
            RequestHandler.handleRequest("unknown");
        } catch (InvalidCommandException e) {
            assertEquals("OOPS. I didn't understand the command. "
                    + "Please key in a valid command", e.getMessage());
        }
    }
}
