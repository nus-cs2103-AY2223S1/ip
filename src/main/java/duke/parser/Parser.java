package duke.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.Duke;
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

    private static final String DEADLINE_SPLIT = "/by";
    private static final String EVENT_SPLIT = "/at";
    private static final String TAG_SPLIT = "/tag";

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
            throw new DukeException("Baa! I don't understand what you mean.");
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
        if (splitInput.length != 2) {
            throw new DukeException("Baa! I need a description for your todo task.");
        }
        if (splitInput[1].trim().length() == 0) {
            throw new DukeException("Baa! I need a description for your todo task.");
        }
        if (!splitInput[1].contains(TAG_SPLIT)) {
            return new AddCommand(new Todo(splitInput[1].trim()));
        }
        String[] contentTagsSplit = splitInput[1].split(TAG_SPLIT, 2);
        if (contentTagsSplit[0].trim().length() == 0) {
            throw new DukeException("Baa! I need a description for your todo task.");
        }
        if (contentTagsSplit[1].trim().length() == 0) {
            throw new DukeException("Baa! I need a name for the tag.");
        }
        String tag = contentTagsSplit[1].trim();
        return new AddCommand(new Todo(contentTagsSplit[0].trim(), tag));
    }

    /**
     * Parse a given string to a add comment for a Deadline task.
     * @param input
     * @return AddCommand(Deadline)
     * @throws DukeException
     */
    public static Command parseDeadlineCommand(String input) throws DukeException {
        String[] splitInput = input.split(" ", 2);
        if (splitInput.length != 2) {
            throw new DukeException("Baa! I need a description for your deadline task.");
        }
        if (splitInput[1].trim().length() == 0) {
            throw new DukeException("Baa! I need a description for your deadline task.");
        }
        String[] splitByDeadline = splitInput[1].split(DEADLINE_SPLIT, 2);
        if (splitByDeadline.length != 2) {
            throw new DukeException("Baa! I need you to follow format 'deadline a deadline /at yyyy-MM-dd'.");
        }
        if (splitByDeadline[0].trim().length() == 0) {
            throw new DukeException("Baa! I need a description for your deadline task.");
        }
        if (splitByDeadline[1].trim().length() == 0) {
            throw new DukeException("Baa! I need a due date for your deadline task.");
        }
        try {
            LocalDate due = DateParser.convertToLocalDate(splitByDeadline[1].trim());
            if (!splitByDeadline[0].contains(TAG_SPLIT)) {
                return new AddCommand(new Deadline(splitByDeadline[0].trim(), due));
            }
            String[] splitByTag = splitByDeadline[0].split(TAG_SPLIT, 2);
            if (splitByTag[0].trim().length() == 0) {
                throw new DukeException("Baa! I need a description for your deadline task.");
            }
            if (splitByTag[1].trim().length() == 0) {
                throw new DukeException("Baa! I need a name for the tag.");
            }
            return new AddCommand(new Deadline(splitByTag[0].trim(), splitByTag[1].trim(), due));
        } catch (DateTimeParseException e) {
            throw new DukeException("Baa! I need you to follow format 'yyyy-mm-dd' for the due date");
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
        if (splitInput.length != 2) {
            throw new DukeException("Baa! I need a description for your event task.");
        }
        if (splitInput[1].trim().length() == 0) {
            throw new DukeException("Baa! I need a description for your event task.");
        }
        String[] splitByEvent = splitInput[1].split(EVENT_SPLIT, 2);
        if (splitByEvent.length != 2) {
            throw new DukeException("Baa! I need you to follow format 'event an event /at yyyy-MM-dd HH:mm'.");
        }
        if (splitByEvent[0].trim().length() == 0) {
            throw new DukeException("Baa! I need a description for your event task.");
        }
        if (splitByEvent[1].trim().length() == 0) {
            throw new DukeException("Baa! I need a time for your event task.");
        }
        try {
            LocalDateTime eventTime = DateParser.convertToLocalDateTime(splitByEvent[1].trim());
            if (!splitByEvent[0].contains(TAG_SPLIT)) {
                return new AddCommand(new Event(splitByEvent[0].trim(), eventTime));
            }
            String[] splitByTag = splitByEvent[0].split(TAG_SPLIT, 2);
            if (splitByTag[0].trim().length() == 0) {
                throw new DukeException("Baa! I need a description for your event task.");
            }
            if (splitByTag[1].trim().length() == 0) {
                throw new DukeException("Baa! I need a name for the tag.");
            }
            return new AddCommand(new Event(splitByTag[0].trim(), splitByTag[1].trim(), eventTime));
        } catch (DateTimeParseException e) {
            throw new DukeException("Baa! I need you to follow format 'yyyy-mm-dd HH:mm' for the event time.");
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
        if (splitInput.length != 2) {
            throw new DukeException("Baa! I need a task number to mark as done.");
        }
        if (splitInput[1].trim().length() == 0) {
            throw new DukeException("Baa! I need to know which task you want to mark as done.");
        }
        try {
            int index = Integer.parseInt(splitInput[1].trim()) - 1;
            return new MarkDoneCommand(index);
        } catch (NumberFormatException e) {
            throw new DukeException("Baa! I need a valid task number.");
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
        if (splitInput.length != 2) {
            throw new DukeException("Baa! I need a task number to unmark.");
        }
        if (splitInput[1].trim().length() == 0) {
            throw new DukeException("Baa! I need to know which task you want to unmark.");
        }
        try {
            int index = Integer.parseInt(splitInput[1].trim()) - 1;
            return new UnmarkDoneCommand(index);
        } catch (NumberFormatException e) {
            throw new DukeException("Baa! I need a valid task number.");
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
        if (splitInput.length != 2) {
            throw new DukeException("Baa! I need a task number to delete.");
        }
        if (splitInput[1].trim().length() == 0) {
            throw new DukeException("Baa! I need to know which task you want to delete.");
        }
        try {
            int index = Integer.parseInt(splitInput[1].trim()) - 1;
            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            throw new DukeException("Baa! I need a valid task number.");
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
        if (splitInput.length != 2) {
            throw new DukeException("Baa! I need a keyword to search.");
        }
        if (splitInput[1].trim().length() == 0) {
            throw new DukeException("Baa! I need a keyword to search.");
        }
        return new FindCommand(splitInput[1].trim());
    }

    private static boolean isValidSplit(String[] split, int splitNumber) {
        return split.length == splitNumber;
    }
}
