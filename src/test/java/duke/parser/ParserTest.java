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

    /**
     * The parse_emptyInput_returnsIncorrect function tests that the parse function
     * returns an ErrorCommand when given empty inputs.
     *
     * @return The following
     */
    @Test
    public void parse_emptyInput_returnsIncorrect() {
        final String[] emptyInputs = { "", "  ", "\n  \n" };
        final String resultMessage = "Please enter a command";
        parseAndAssertIncorrectWithMessage(resultMessage, emptyInputs);
    }

    /**
     * The parse_unknownCommandWord_returnsError function tests that the parse
     * function returns an ErrorCommand
     * when given a string containing an unknown command word.
     *
     * @return An errorcommand
     */
    @Test
    public void parse_unknownCommandWord_returnsError() {
        final String input = "unknown command";
        parseAndAssertCommandType(
                input, ErrorCommand.class);
    }

    /**
     * The parseAndAssertIncorrectWithMessage function is a helper function that
     * parses the given input and asserts that it is an ErrorCommand with the
     * expected feedback message. The inputs are passed as varargs, so this function
     * can be used to test multiple inputs at once. This is useful for testing error
     * messages.
     *
     * @param feedbackMessage
     *            Provide feedback to the user
     * @param String...
     *            Pass an array of strings to the function
     * @return An errorcommand
     */
    private void parseAndAssertIncorrectWithMessage(
            String feedbackMessage, String... inputs) {
        for (String input : inputs) {
            final ErrorCommand result = parseAndAssertCommandType(
                    input, ErrorCommand.class);
            assertEquals(result.getMessage(), feedbackMessage);
        }
    }

    /**
     * The parseAndAssertCommandType function takes a string input and an expected
     * command class.
     * It parses the input using the parser, and asserts that the resulting command
     * is of type
     * expectedCommandClass. If it is not, then it throws an AssertionError with a
     * helpful message.
     *
     * @param input
     *            Pass the input to the parseandassertcommandtype function
     * @param Class&lt;T&gt;
     *            Specify the type of command that is expected to be returned
     * @return A command of the expected type
     */
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
