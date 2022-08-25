package duke.parser;

import duke.command.*;
import duke.exception.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * A parser class that encapsulates the action of parsing string to desired Object.
 */
public class Parser {

    private static final int TASK_TYPE = 0;
    private static final int COMMAND_DESCRIPTION = 1;

    /***
     * Parses the user input to relevant command.
     * @param fullCommand User input
     * @return Command that needs to be executed.
     * @throws DukeException throws an exception when there is unexpected input.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] arr = fullCommand.trim().split(" ", 2);
        String taskType = arr[TASK_TYPE];
        switch (taskType.toUpperCase()) {
            case "BYE":
                return new ExitCommand();

            case "LIST":
                return new ListCommand();

            case "MARK":
                int indexOfTaskToMark = parseToTaskIndex(arr);
                return new MarkCommand(true, indexOfTaskToMark);

            case "UNMARK":
                int indexOfTaskToUnmark = parseToTaskIndex(arr);
                return new MarkCommand(false, indexOfTaskToUnmark);

            case "DELETE":
                int indexOfTaskToDelete = parseToTaskIndex(arr);
                return new DeleteCommand(indexOfTaskToDelete);

            case "TODO":
                return parseToAddTodoCommand(arr);

            case "DEADLINE":
                return parseToAddDeadlineCommand(arr);

            case "EVENT":
                return parseToAddEventCommand(arr);

            case "FIND":
                return parseToFindCommand(arr);

            case "HELP":
                return new HelpCommand();

            default:
                throw new InvalidCommandException();
        }

    }

    /**
     * Parses the string command into index if possible.
     * @param fullCommandArray user input
     * @return the index of a task
     * @throws EmptyIndexException When user did not include an index.
     */
    public static int parseToTaskIndex(String[] fullCommandArray) throws IllegalInputException {
        if (fullCommandArray.length == 1) {
            throw new EmptyIndexException();
        }
        if (fullCommandArray[COMMAND_DESCRIPTION].matches("\\d+")) {
            return Integer.parseInt(fullCommandArray[COMMAND_DESCRIPTION]);
        } else {
            throw new InvalidIndexException();
        }
    }

    /**
     * Parses the string command into AddCommand that adds a Todo_task
     * @param fullCommandArray user input
     * @return AddCommand instance
     * @throws EmptyDescriptionException throws exception when the description of Todo_task is not given.
     */
    private static Command parseToAddTodoCommand(String[] fullCommandArray) throws EmptyDescriptionException {
        if (fullCommandArray.length == 1) {
            throw new EmptyDescriptionException();
        }
        return new AddCommand(new Todo(fullCommandArray[COMMAND_DESCRIPTION]));
    }

    private static Command parseToAddDeadlineCommand(String[] fullCommandArray) throws IllegalInputException {

        simpleDescriptionChecking(fullCommandArray);

        String descriptionAndDate = fullCommandArray[COMMAND_DESCRIPTION];
        String[] descriptionAndDateArray = descriptionAndDate.split("/");

        if (descriptionAndDateArray.length == 1) {
            throw new EmptyDateException();
        }

        String description = descriptionAndDateArray[0];
        LocalDate date = parseToLocalDateTime(descriptionAndDateArray[1]);
        return new AddCommand(new Deadline(description, date));
    }

    private static Command parseToAddEventCommand(String[] fullCommandArray) throws IllegalInputException {

        simpleDescriptionChecking(fullCommandArray);

        String descriptionAndDate = fullCommandArray[COMMAND_DESCRIPTION];
        String[] descriptionAndDateArray = descriptionAndDate.split("/");

        if (descriptionAndDateArray.length == 1) {
            throw new EmptyDateException();
        }

        String description = descriptionAndDateArray[0];
        LocalDate date = parseToLocalDateTime(descriptionAndDateArray[1]);
        return new AddCommand(new Event(description, date));
    }


    /**
     * Parses the input String into LocalDate instance
     * @param date the date that the user has entered.
     * @return a local date object
     * @throws IllegalDateFormatException throws an exception when the input date is not of correct format.
     */
    public static LocalDate parseToLocalDateTime(String date) throws IllegalDateFormatException {
        date = date.trim();
        LocalDate res = null;
        try {
            res = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new IllegalDateFormatException();
        }
        return res;
    }

    private static Command parseToFindCommand(String[] fullCommandArray) throws EmptyDescriptionException {

        simpleDescriptionChecking(fullCommandArray);

        String description = fullCommandArray[COMMAND_DESCRIPTION];
        return new FindCommand(description);
    }

    private static void simpleDescriptionChecking(String[] fullCommandArray) throws EmptyDescriptionException {
        if (fullCommandArray.length == 1) {
            throw new EmptyDescriptionException();
        }
    }
}
