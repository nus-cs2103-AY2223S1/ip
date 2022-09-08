package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.DukeException;
import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkDoneCommand;
import duke.command.UnmarkDoneCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * Parser to parse the input from users.
 */
public class Parser {
    public static final String EXIT_PREFIX = "bye";
    public static final String LIST_PREFIX = "list";
    public static final String TODO_PREFIX = "todo";
    public static final String DEADLINE_PREFIX = "deadline";
    public static final String EVENT_PREFIX = "event";
    public static final String MARK_DONE_PREFIX = "mark";
    public static final String UNMARK_DONE_PREFIX = "unmark";
    public static final String DELETE_PREFIX = "delete";
    public static final String FIND_PREFIX = "find";

    private static final String DEADLINE_SPLIT = " /by ";
    private static final String EVENT_SPLIT = " /at ";

    /**
     * Parse an input string from user to a suitable command.
     * @param input
     * @return Command
     * @throws DukeException
     */
    public static Command parse(String input) throws DukeException {
        if (input.startsWith(EXIT_PREFIX)) {
            return new ExitCommand();
        } else if (input.startsWith(LIST_PREFIX)) {
            return new ListCommand();
        } else if (input.startsWith(TODO_PREFIX)) {
            return parseTodoCommand(input);
        } else if (input.startsWith(DEADLINE_PREFIX)) {
            return parseDeadlineCommand(input);
        } else if (input.startsWith(EVENT_PREFIX)) {
            return parseEventCommand(input);
        } else if (input.startsWith(MARK_DONE_PREFIX)) {
            return parseMarkDoneCommand(input);
        } else if (input.startsWith(UNMARK_DONE_PREFIX)) {
            return parseUnmarkDoneCommand(input);
        } else if (input.startsWith(DELETE_PREFIX)) {
            return parseDeleteCommand(input);
        } else if (input.startsWith(FIND_PREFIX)) {
            return parseFindCommand(input);
        } else {
            throw new DukeException("OOPS!!! I don't understand what you mean.");
        }
    }

    /**
     * Parse a given string to a add comment for a Todo task.
     * @param input
     * @return AddCommand(Todo)
     * @throws DukeException
     */
    public static Command parseTodoCommand(String input) throws DukeException {
        String[] splitInput = input.split(" ", 2);
        assert splitInput.length != 2 : "OOPS!!! Invalid todo command.";
        assert splitInput[1].trim().length() == 0: "OOPS!!! duke.task.Todo description cannot be empty.";
        return new AddCommand(new Todo(splitInput[1]));
    }

    /**
     * Parse a given string to a add comment for a Deadline task.
     * @param input
     * @return AddCommand(Deadline)
     * @throws DukeException
     */
    public static Command parseDeadlineCommand(String input) throws DukeException {
        String[] splitInput = input.split(" ", 2);
        assert splitInput.length != 2 : "OOPS!!! Invalid deadline command.";
        assert splitInput[1].trim().length() == 0: "OOPS!!! duke.task.Deadline description cannot be empty.";
        String[] splitInputByDeadlineSplit = splitInput[1].split(DEADLINE_SPLIT, 2);
        if (!isValidSplit(splitInputByDeadlineSplit, 2)) {
            throw new DukeException("OOPS!!! Invalid deadline command.");
        }
        if (splitInputByDeadlineSplit[0].trim().length() == 0) {
            throw new DukeException("OOPS!!! duke.task.Deadline description cannot be empty.");
        }
        if (splitInputByDeadlineSplit[1].trim().length() == 0) {
            throw new DukeException("OOPS!!! duke.task.Deadline due date cannot be empty.");
        }
        try {
            LocalDate due = DateParser.convertToLocalDate(splitInputByDeadlineSplit[1].trim());
            return new AddCommand(new Deadline(splitInputByDeadlineSplit[0].trim(), due));
        } catch (DateTimeParseException e) {
            throw new DukeException("OOPS!!! duke.task.Deadline due date format is wrong. "
                    + "Follow format yyyy-mm-dd for due date");
        }
    }

    /**
     * Parse a given string to a add comment for a Event task.
     * @param input
     * @return AddCommand(Event)
     * @throws DukeException
     */
    public static Command parseEventCommand(String input) throws DukeException {
        String[] splitInput = input.split(" ", 2);
        assert splitInput.length != 2 : "OOPS!!! Invalid event command.";
        assert splitInput[1].trim().length() == 0: "OOPS!!! duke.task.Event description cannot be empty.";
        String[] splitInputByEventSplit = splitInput[1].split(EVENT_SPLIT, 2);
        if (!isValidSplit(splitInputByEventSplit, 2)) {
            throw new DukeException("OOPS!!! Invalid event command.");
        }
        if (splitInputByEventSplit[0].trim().length() == 0) {
            throw new DukeException("OOPS!!! duke.task.Event description cannot be empty.");
        }
        if (splitInputByEventSplit[1].trim().length() == 0) {
            throw new DukeException("OOPS!!! duke.task.Event date time cannot be empty.");
        }
        try {
            return new AddCommand(new Event(splitInputByEventSplit[0].trim(), splitInputByEventSplit[1].trim()));
        } catch (DateTimeParseException e) {
            throw new DukeException("OOPS!!! duke.task.Event date time format is wrong.");
        }
    }

    /**
     * Parse a given string to a MarkDoneCommand to mark a task as done.
     * @param input
     * @return MarkDoneCommand
     * @throws DukeException
     */
    public static Command parseMarkDoneCommand(String input) throws DukeException {
        String[] splitInput = input.split(" ", 2);
        if (splitInput[1].trim().length() == 0) {
            throw new DukeException("OOPS!!! There is no task number to be marked as done.");
        }
        try {
            int index = Integer.parseInt(splitInput[1].trim()) - 1;
            return new MarkDoneCommand(index);
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! The task number format is invalid.");
        }
    }

    /**
     * Parse a given string to a UnmarkDoneCommand to unmark a marked task.
     * @param input
     * @return UnmarkDoneCommand
     * @throws DukeException
     */
    public static Command parseUnmarkDoneCommand(String input) throws DukeException {
        String[] splitInput = input.split(" ", 2);
        if (splitInput[1].trim().length() == 0) {
            throw new DukeException("OOPS!!! There is no task number to be marked as not done.");
        }
        try {
            int index = Integer.parseInt(splitInput[1].trim()) - 1;
            return new UnmarkDoneCommand(index);
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! The task number format is invalid.");
        }
    }
    /**
     * Parse a given string to a DeleteCommand to delete a task from the task list.
     * @param input
     * @return DeleteCommand
     * @throws DukeException
     */
    public static Command parseDeleteCommand(String input) throws DukeException {
        String[] splitInput = input.split(" ", 2);
        if (splitInput[1].trim().length() == 0) {
            throw new DukeException("OOPS!!! There is no task number to be deleted.");
        }
        try {
            int index = Integer.parseInt(splitInput[1].trim()) - 1;
            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! The task number format is invalid.");
        }
    }

    /**
     * Parse a given string to a FindCommand to find a task by searching the keyword.
     * @param input
     * @return FindCommand
     * @throws DukeException
     */
    public static Command parseFindCommand(String input) throws DukeException {
        String[] splitInput = input.split(" ", 2);
        assert splitInput.length != 2 : "OOPS!!! Invalid deadline command.";
        assert splitInput[1].trim().length() == 0: "OOPS!!! duke.task.Deadline description cannot be empty.";
        return new FindCommand(splitInput[1].trim());
    }

    private static boolean isValidSplit(String[] split, int splitNumber) {
        return split.length == splitNumber;
    }
}
