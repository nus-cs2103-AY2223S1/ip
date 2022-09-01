package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

/**
 * Represents a parser for user input given to Duke chatbot.
 *
 * @author Conrad
 */
public class Parser {

    /**
     * Parses the task number from the user input command into its <code>TaskList</code> index.
     *
     * @param userInput String input provided by user.
     * @return An integer representing the index of the task in the <code>TaskList</code>.
     * @throws DukeException If input is not of the type [command] [taskNumber]
     */
    public static int parseTaskNumber(String userInput) throws DukeException {
        String[] parsedInput = userInput.split(" ");
        String taskNumberString = parsedInput[1];
        try {
            return Integer.parseInt(taskNumberString) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("   " + taskNumberString + " is not a valid task number.\n");
        }

    }

    /**
     * Parses the user input into a <code>Todo</code> task.
     *
     * @param userInput String input provided by user.
     * @return An <code>Todo</code> task with the specified description.
     */
    public static Todo parseTodoTask(String userInput) {
        String[] parsedUserResponse = userInput.split(" ");
        String newTaskDescription = String.join(" ",
                Arrays.copyOfRange(parsedUserResponse, 1, parsedUserResponse.length));
        return new Todo(newTaskDescription);
    }

    /**
     * Parses the user input into an <code>Event</code> task.
     *
     * @param userInput String input provided by user.
     * @return An <code>Event</code> task with the specified description and time of occurrence.
     */
    public static Event parseEventTask(String userInput) {

        String[] parsedUserResponse = userInput.split(" ");
        int atSeparationIndex = Arrays.asList(parsedUserResponse).indexOf("/at");
        String newTaskDescription = String.join(" ",
                Arrays.copyOfRange(parsedUserResponse,
                        1,
                        atSeparationIndex));
        String newEventTime = String.join(" ",
                Arrays.copyOfRange(parsedUserResponse,
                        atSeparationIndex + 1,
                        parsedUserResponse.length));
        return new Event(newTaskDescription, newEventTime);
    }

    /**
     * Parses the user input into a <code>Deadline</code> task.
     *
     * @param userInput String input provided by user.
     * @return An <code>Deadline</code> task with the specified description and deadline.
     */
    public static Deadline parseDeadlineTask(String userInput) throws DukeException {
        String[] parsedUserResponse = userInput.split(" ");
        int bySeparationIndex = Arrays.asList(parsedUserResponse).indexOf("/by");
        String newTaskDescription = String.join(" ",
                Arrays.copyOfRange(parsedUserResponse,
                        1,
                        bySeparationIndex));
        try {
            LocalDate deadline = LocalDate.parse(parsedUserResponse[bySeparationIndex + 1]);
            return new Deadline(newTaskDescription, deadline);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("    " + "Error: No date added for the deadline.\n");
        } catch (DateTimeParseException e) {
            throw new DukeException("    "
                    + "Error: Invalid deadline format, please key in as yyyy-mm-dd\n");
        }
    }

    /**
     * Parses the user input into a <code>Command</code>.
     *
     * @param userResponse String input provided by user.
     * @return A <code>Command</code> representing the action requested from Duke chatbot.
     */
    public static Command parseUserResponse(String userResponse) throws DukeException {
        String[] parsedUserResponse = userResponse.split(" ");
        String mainCommand = parsedUserResponse[0];
        switch(mainCommand) {
        case "bye":
            if (parsedUserResponse.length > 1) {
                throw new DukeException("Invalid number of arguments, only one required\n");
            } else {
                return Command.BYE;
            }
        case "list":
            if (parsedUserResponse.length > 1) {
                throw new DukeException("Invalid number of arguments, only one required\n");
            } else {
                return Command.LIST;
            }
        case "mark":
            if (parsedUserResponse.length != 2) {
                throw new DukeException("Invalid number of arguments, two required\n");
            } else {
                return Command.MARK;
            }
        case "unmark":
            if (parsedUserResponse.length != 2) {
                throw new DukeException("Invalid number of arguments, two required\n");
            } else {
                return Command.UNMARK;
            }
        case "todo":
            if (parsedUserResponse.length < 2) {
                throw new DukeException("The description of a todo cannot be empty.\n");
            } else {
                return Command.TODO;
            }
        case "deadline":
            int bySeparationIndex = Arrays.asList(parsedUserResponse).indexOf("/by");
            if (bySeparationIndex == -1) {
                throw new DukeException("Error: No date added for the deadline.\n");
            } else {
                return Command.DEADLINE;
            }
        case "event":
            int atSeparationIndex = Arrays.asList(parsedUserResponse).indexOf("/at");
            if (atSeparationIndex == -1) {
                throw new DukeException("Error: No date added for the event.\n");
            } else {
                return Command.EVENT;
            }
        case "delete":
            if (parsedUserResponse.length != 2) {
                throw new DukeException("Invalid number of arguments, two required\n");
            } else {
                return Command.DELETE;
            }
        case "find":
            if (parsedUserResponse.length < 2) {
                throw new DukeException("Invalid number of arguments, at least two required\n");
            } else {
                return Command.FIND;
            }
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(\n");
        }
    }

    /**
     * Parses the user input into a query string to be matched.
     *
     * @param userInput
     * @return A query string.
     */
    public static String parseSearchInput(String userInput) {
        String[] parsedUserResponse = userInput.split(" ");
        String searchInput = String.join(" ", Arrays.copyOfRange(parsedUserResponse, 1, parsedUserResponse.length));
        return searchInput;
    }
}
