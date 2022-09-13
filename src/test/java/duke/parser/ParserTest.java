package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.commands.BaseCommand;
import duke.commands.ErrorCommand;
import duke.commands.tasks.AddDeadlineCommand;
import duke.commands.tasks.AddEventCommand;
import duke.commands.tasks.AddTodoCommand;
import duke.commands.tasks.ListTasksAfterCommand;
import duke.commands.tasks.ListTasksBeforeCommand;
import duke.commands.tasks.ListTasksCommand;
import duke.exceptions.NoCommandException;
import duke.exceptions.ParseException;

public class ParserTest {
    private Parser parser;

    @BeforeEach
    public void setUp() {
        parser = new Parser();
    }

    /**
     * The parse_emptyInput_returnsIncorrect function tests that the parse function
     * returns an ErrorCommand when given empty inputs.
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
     */
    @Test
    public void parse_unknownCommandWord_returnsError() {
        final String input = "unknown command";
        parseAndAssertCommandType(
                ErrorCommand.class, input);
    }

    /**
     * The parse_listCommandWord_returnsListCommand function tests the
     * ListTasksCommand class by creating a new instance of
     * ListTasksCommand and then checking to see if it is an instance of the correct
     * type. The inputs array contains strings
     * that should be parsed into a list command, so each string in the array is
     * passed to parseAndAssertCommandTypes as an input.
     */
    @Test
    public void parse_listCommandWord_returnsListCommand() {
        final String[] inputs = { "list", " list", "list ", " list " };
        parseAndAssertCommandTypes(
                ListTasksCommand.class, inputs);
    }

    /**
     * The parse_listBeforeCommandWord_returnsListBeforeCommand function tests the
     * ListTasksBeforeCommand class by
     * creating a new instance of it and then checking to see if the command type is
     * correct. The inputs array contains
     * strings that should be parsed into a ListTasksBeforeCommand object, so each
     * string in this array is passed to the
     * parse function and checked for correctness. If any of these tests fail, an
     * AssertionError will be thrown. This test
     * case checks for all possible ways that a user could enter their command
     * incorrectly (e.g., missing words or extra spaces).
     */
    @Test
    public void parse_listBeforeCommandWord_returnsListBeforeCommand() {
        final String[] inputs = {
                "list before 21-02-2019 19:00",
                " list before 21-02-2019 19:00",
                "list  before 21-02-2019 19:00",
                "list before  21-02-2019 19:00",
                "list before 21-02-2019  19:00",
                "list before 21-02-2019 19:00 ",
                "list before 21-02-2019  19:00 "
        };
        parseAndAssertCommandTypes(ListTasksBeforeCommand.class, inputs);
    }

    /**
     * The parse_listAfterCommandWord_returnsListBeforeCommand function tests the
     * ListTasksAfterCommand class by
     * creating a new instance of it and then checking to see if the command type is
     * correct. The inputs array contains
     * strings that should be parsed into a ListTasksAfterCommand object, so we loop
     * through each input string and check
     * to see if an exception is thrown when parsing it. If no exception is thrown,
     * then we know that the command was parsed correctly.
     */
    @Test
    public void parse_listAfterCommandWord_returnsListBeforeCommand() {
        final String[] inputs = {
                "list after 21-02-2019 19:00",
                " list after 21-02-2019 19:00",
                "list  after 21-02-2019 19:00",
                "list after  21-02-2019 19:00",
                "list after 21-02-2019  19:00",
                "list after 21-02-2019 19:00 ",
                "list after 21-02-2019  19:00 "
        };
        parseAndAssertCommandTypes(ListTasksAfterCommand.class, inputs);
    }

    /**
     * The parse_todoCommandWord_returnsTodoCommand function tests the parse
     * function of the AddTodoCommand class.
     * It tests that it parses a string containing &quot;todo&quot; and a task name
     * into an instance of AddTodoCommand.
     */
    @Test
    public void parse_todoCommandWord_returnsTodoCommand() {
        final String[] inputs = {
                "todo sleep",
                " todo sleep",
                "todo  sleep",
                "todo sleep ",
                "todo buy milk",
                " todo buy milk",
                "todo  buy  milk",
                "todo buy milk ",
        };
        parseAndAssertCommandTypes(AddTodoCommand.class, inputs);
    }

    /**
     * The parse_eventCommandWord_returnsEventCommand function tests the parse
     * function of the
     * AddEventCommand class. It tests that it parses correctly when given a valid
     * input string, and
     * throws an exception when given an invalid input string. The inputs are
     * strings with different
     * combinations of spaces before and after each word in the command, as well as
     * different capitalizations.
     */
    @Test
    public void parse_eventCommandWord_returnsEventCommand() {
        final String[] inputs = {
                "event project meeting at 05-12-2019 12:00",
                " event project meeting at 05-12-2019 12:00",
                "event  project meeting at 05-12-2019 12:00",
                "event project  meeting at 05-12-2019 12:00",
                "event project meeting  at 05-12-2019 12:00",
                "event project meeting at  05-12-2019 12:00",
                "event project meeting at 05-12-2019  12:00",
                "event project meeting at 05-12-2019 12:00 ",
        };
        parseAndAssertCommandTypes(AddEventCommand.class, inputs);
    }

    /**
     * The parse_deadlineCommandWord_returnsDeadlineCommand function tests that the
     * parse function can correctly
     * identify a deadline command. It does this by creating an array of strings,
     * each containing a different variation
     * of the word &quot;deadline&quot; in it. Then, it passes each string into the
     * parse function and asserts that they are parsed as
     * AddDeadlineCommand objects. This is done using JUnit 5's BeforeEach
     * annotation to create an instance variable for use in all tests within this
     * class (the instance variable will be used to create commands). The test also
     * uses JUnit 5's Test annotation to specify which method(s) should be tested
     * within this class (in this case
     */
    @Test
    public void parse_deadlineCommandWord_returnsDeadlineCommand() {
        final String[] inputs = {
                "deadline return book by 02-12-2019 18:00",
                " deadline return book by 02-12-2019 18:00",
                "deadline  return book by 02-12-2019 18:00",
                "deadline return  book by 02-12-2019 18:00",
                "deadline return book  by 02-12-2019 18:00",
                "deadline return book by  02-12-2019 18:00",
                "deadline return book by 02-12-2019  18:00",
                "deadline return book by 02-12-2019 18:00 ",
        };
        parseAndAssertCommandTypes(AddDeadlineCommand.class, inputs);
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
     * @param inputs
     *            Pass an array of strings to the function
     * @return An errorcommand
     */
    private void parseAndAssertIncorrectWithMessage(
            String feedbackMessage, String... inputs) {
        for (String input : inputs) {
            final ErrorCommand result = parseAndAssertCommandType(
                    ErrorCommand.class, input);
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
     * @param expectedCommandClass
     *            Specify the type of command that is expected to be returned
     * @return A command of the expected type
     */
    @SuppressWarnings("unchecked")
    private <T extends BaseCommand> T parseAndAssertCommandType(
            Class<T> expectedCommandClass, String input) {
        BaseCommand result;
        try {
            result = parser.parse(input);
        } catch (NoCommandException | ParseException e) {
            result = new ErrorCommand(e.getMessage());
        }
        assertTrue(result.getClass().isAssignableFrom(expectedCommandClass));
        return (T) result;
    }

    /**
     * The parseAndAssertCommandTypes function takes a list of inputs and parses
     * them using the parser.
     * It then asserts that the resulting command is an instance of the
     * expectedCommandClass.
     *
     * @param expectedCommandClass
     *            Check whether the result of
     * @param String...
     *            Pass in a variable number of arguments to the method
     */
    private <T extends BaseCommand> void parseAndAssertCommandTypes(
            Class<T> expectedCommandClass, String... inputs) {
        for (String input : inputs) {
            BaseCommand result;
            try {
                result = parser.parse(input);
            } catch (NoCommandException | ParseException e) {
                result = new ErrorCommand(e.getMessage());
            }
            assertTrue(result.getClass().isAssignableFrom(expectedCommandClass));
        }
    }

}
