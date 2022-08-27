package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.commands.BaseCommand;
import duke.commands.ErrorCommand;
import duke.exceptions.IncorrectArgumentException;
import duke.exceptions.InvalidDateTimeException;
import duke.exceptions.InvalidTaskSpecificationException;
import duke.exceptions.MissingArgumentException;
import duke.exceptions.NoCommandException;
import duke.exceptions.UnknownCommandException;

public class ParserTest {
    private Parser parser;

    @BeforeEach
    public void setUp() {
        parser = new Parser();
    }

    @Test
    public void parse_emptyInput_returnsIncorrect() {
        final String[] emptyInputs = { "", "  ", "\n  \n" };
        final String resultMessage = "Please enter a command";
        parseAndAssertIncorrectWithMessage(resultMessage, emptyInputs);
    }

    @Test
    public void parse_unknownCommandWord_returnsError() {
        final String input = "unknown command";
        parseAndAssertCommandType(
                input, ErrorCommand.class);
    }

    private void parseAndAssertIncorrectWithMessage(
            String feedbackMessage, String... inputs) {
        for (String input : inputs) {
            final ErrorCommand result = parseAndAssertCommandType(
                    input, ErrorCommand.class);
            assertEquals(result.getMessage(), feedbackMessage);
        }
    }

    @SuppressWarnings("unchecked")
    private <T extends BaseCommand> T parseAndAssertCommandType(
            String input, Class<T> expectedCommandClass) {
        BaseCommand result;
        try {
            result = parser.parse(input);
        } catch (MissingArgumentException | InvalidDateTimeException | InvalidTaskSpecificationException
                | IncorrectArgumentException | UnknownCommandException | NoCommandException e) {
            result = new ErrorCommand(e.getMessage());
        }
        assertTrue(result.getClass().isAssignableFrom(expectedCommandClass));
        return (T) result;
    }

}
