package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.RescheduleCommand;
import duke.exception.DukeException;
import duke.exception.EmptyDateException;
import duke.exception.EmptyDescriptionException;
import duke.exception.EmptyIndexException;
import duke.exception.IllegalDateFormatException;
import duke.exception.IllegalInputException;
import duke.exception.IllegalTaskTypeException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidIndexException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * A parser class that encapsulates the action of parsing string to desired Object.
 */
public class Parser {

    private static final int TASK_TYPE = 0;
    private static final int DESCRIPTION = 1;

    /***
     * Parses the user input to relevant command.
     * @param fullCommand User input
     * @return Command that needs to be executed.
     * @throws DukeException throws an exception when there is unexpected input.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] arr = fullCommand.trim().split("\\s+", 2);
        String taskType = arr[TASK_TYPE];
        switch (taskType.toUpperCase()) {
        case "BYE":
            return new ExitCommand();

        case "LIST":
            return new ListCommand();

        case "MARK":
            simpleTaskIndexCheck(arr);
            int indexOfTaskToMark = parseToTaskIndex(arr[DESCRIPTION]);
            return new MarkCommand(true, indexOfTaskToMark);

        case "UNMARK":
            simpleTaskIndexCheck(arr);
            int indexOfTaskToUnmark = parseToTaskIndex(arr[DESCRIPTION]);
            return new MarkCommand(false, indexOfTaskToUnmark);

        case "DELETE":
            simpleTaskIndexCheck(arr);
            int indexOfTaskToDelete = parseToTaskIndex(arr[DESCRIPTION]);
            return new DeleteCommand(indexOfTaskToDelete);

        case "TODO":
            return parseToAddTodoCommand(arr);

        case "DEADLINE":
            return parseToAddDeadlineCommand(arr);

        case "EVENT":
            return parseToAddEventCommand(arr);

        case "FIND":
            return parseToFindCommand(arr);

        case "RESCHEDULE":
            return parseToRescheduleCommand(arr);

        case "HELP":
            return new HelpCommand();

        default:
            throw new InvalidCommandException();
        }

    }

    private static Command parseToRescheduleCommand(String[] arr) throws DukeException {
        simpleDescriptionChecking(arr);

        String rescheduleDescription = arr[DESCRIPTION];
        String[] newStrArr = rescheduleDescription.trim().split("\\s+", 2);

        simpleDescriptionChecking(newStrArr);

        int taskIndex = parseToTaskIndex(newStrArr[0]);

        String newTask = newStrArr[1];
        String[] fullCommandArray = newTask.split("\\s+", 2);

        Task rescheduledTask = getRescheduledTask(fullCommandArray);

        return new RescheduleCommand(taskIndex, rescheduledTask);

    }

    private static Task getRescheduledTask(String[] fullCommandArray) throws DukeException {
        simpleDescriptionChecking(fullCommandArray);

        String taskType = fullCommandArray[TASK_TYPE].trim().toUpperCase();

        switch (taskType) {
        case "TODO":
            return new Todo(fullCommandArray[DESCRIPTION].trim());
        case "DEADLINE":
            return getDeadlineTask(fullCommandArray);
        case "EVENT":
            return getEventTask(fullCommandArray);
        default:
            throw new IllegalTaskTypeException();
        }
    }

    /**
     * Parses the string command into index if possible.
     *
     * @param commandDescription command description
     * @return the index of a task
     * @throws IllegalInputException When user did not include an index.
     */
    protected static int parseToTaskIndex(String commandDescription) throws IllegalInputException {
        if (commandDescription.matches("\\d+")) {
            return Integer.parseInt(commandDescription);
        } else {
            throw new InvalidIndexException();
        }
    }

    /**
     * Parses the string command into AddCommand that adds a Todo_task
     *
     * @param fullCommandArray user input
     * @return AddCommand instance
     * @throws EmptyDescriptionException throws exception when the description of Todo_task is not given.
     */
    private static Command parseToAddTodoCommand(String[] fullCommandArray) throws EmptyDescriptionException {
        simpleDescriptionChecking(fullCommandArray);
        return new AddCommand(new Todo(fullCommandArray[DESCRIPTION].trim()));
    }

    private static Command parseToAddDeadlineCommand(String[] fullCommandArray) throws IllegalInputException {

        simpleDescriptionChecking(fullCommandArray);

        Deadline deadlineTask = getDeadlineTask(fullCommandArray);

        return new AddCommand(deadlineTask);
    }

    private static Deadline getDeadlineTask(String[] fullCommandArray) throws EmptyDateException,
                IllegalDateFormatException {

        String descriptionAndDate = fullCommandArray[DESCRIPTION].trim();
        String[] descriptionAndDateArray = descriptionAndDate.split("/");

        simpleDescriptionAndDateChecking(descriptionAndDateArray);

        String description = descriptionAndDateArray[0].trim();
        LocalDate date = parseToLocalDateTime(descriptionAndDateArray[1]);

        return new Deadline(description, date);
    }


    private static Command parseToAddEventCommand(String[] fullCommandArray) throws IllegalInputException {

        simpleDescriptionChecking(fullCommandArray);

        Event eventTask = getEventTask(fullCommandArray);

        return new AddCommand(eventTask);
    }

    private static Event getEventTask(String[] fullCommandArray) throws EmptyDateException, IllegalDateFormatException {
        String descriptionAndDate = fullCommandArray[DESCRIPTION].trim();
        String[] descriptionAndDateArray = descriptionAndDate.split("/");

        simpleDescriptionAndDateChecking(descriptionAndDateArray);

        String description = descriptionAndDateArray[0].trim();
        LocalDate date = parseToLocalDateTime(descriptionAndDateArray[1]);
        return new Event(description, date);
    }


    /**
     * Parses the input String into LocalDate instance
     *
     * @param date the date that the user has entered.
     * @return a local date object
     * @throws IllegalDateFormatException throws an exception when the input date is not of correct format.
     */
    public static LocalDate parseToLocalDateTime(String date) throws IllegalDateFormatException {
        date = date.trim().substring(3);
        LocalDate res;
        try {
            res = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new IllegalDateFormatException();
        }
        return res;
    }

    private static Command parseToFindCommand(String[] fullCommandArray) throws EmptyDescriptionException {

        simpleDescriptionChecking(fullCommandArray);

        String description = fullCommandArray[DESCRIPTION];
        return new FindCommand(description);
    }

    private static void simpleDescriptionChecking(String[] fullCommandArray) throws EmptyDescriptionException {
        assert fullCommandArray.length > 0;
        if (fullCommandArray.length == 1) {
            throw new EmptyDescriptionException();
        }
    }

    private static void simpleDescriptionAndDateChecking(String[] descriptionAndDateArray) throws EmptyDateException {
        assert descriptionAndDateArray.length > 0;
        if (descriptionAndDateArray.length == 1) {
            throw new EmptyDateException();
        }
    }

    private static void simpleTaskIndexCheck(String[] fullCommandArray) throws EmptyIndexException {
        if (fullCommandArray.length == 1) {
            throw new EmptyIndexException();
        }
    }
}
