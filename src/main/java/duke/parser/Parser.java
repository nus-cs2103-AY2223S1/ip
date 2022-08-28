package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.Command;
import duke.command.DeleteTaskCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkTaskCommand;
import duke.command.UnmarkTaskCommand;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Parser for the Duke program.
 */
public class Parser {
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    public static final Pattern DEADLINE_ARGS_FORMAT = Pattern.compile("(?<desc>.+)/by(?<date>.+)");
    public static final Pattern EVENT_ARGS_FORMAT = Pattern.compile("(?<desc>.+)/at(?<date>.+)");
    public static final Pattern STORAGE_FORMAT = Pattern.compile(
            "(?<taskType>[TDE])\\|(?<done>[01])\\|(?<desc>[^|]+)\\|?(?<date>.+)?");

    /**
     * Parses a command entered by the user.
     * @param commandString Command entered by user.
     * @return Command object that can be executed.
     */
    public static Command parseCommand(String commandString) {
        Matcher matcher = BASIC_COMMAND_FORMAT.matcher(commandString.trim());
        if (!matcher.matches()) {
            throw new DukeException("Unknown command");
        }
        String commandWord = matcher.group("commandWord");
        String arguments = matcher.group("arguments");

        switch(commandWord) {
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case MarkTaskCommand.COMMAND_WORD:
            return new MarkTaskCommand(Parser.parseTaskIndex(arguments));
        case UnmarkTaskCommand.COMMAND_WORD:
            return new UnmarkTaskCommand(Parser.parseTaskIndex(arguments));
        case AddTodoCommand.COMMAND_WORD:
            return Parser.prepareTodo(arguments);
        case AddDeadlineCommand.COMMAND_WORD:
            return Parser.prepareDeadline(arguments);
        case AddEventCommand.COMMAND_WORD:
            return Parser.prepareEvent(arguments);
        case DeleteTaskCommand.COMMAND_WORD:
            return new DeleteTaskCommand(Parser.parseTaskIndex(arguments));
        case FindCommand.COMMAND_WORD:
            return new FindCommand(arguments);
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        default:
            throw new DukeException("Unknown command");
        }
    }

    /**
     * Parses a string representing a task stored in the hard disk.
     * @param taskString String representation of the task.
     * @return Task object.
     */
    public static Task fromStorage(String taskString) {
        Matcher matcher = STORAGE_FORMAT.matcher(taskString);
        if (!matcher.matches()) {
            throw new DukeException("Invalid task format in storage: " + taskString);
        }
        String taskType = matcher.group("taskType");
        String done = matcher.group("done");
        String desc = matcher.group("desc");
        String date = matcher.group("date");

        Task newTask;
        switch (taskType) {
        case "T":
            newTask = new Todo(desc);
            break;
        case "D":
            newTask = new Deadline(desc, LocalDate.parse(date, Deadline.INPUT_DATE_FORMAT));
            break;
        case "E":
            newTask = new Event(desc, LocalDate.parse(date, Deadline.INPUT_DATE_FORMAT));
            break;
        default:
            throw new DukeException("Invalid task format in storage: " + taskString);
        }
        if (done.equals("1")) {
            newTask.markAsDone();
        }
        return newTask;
    }

    private static Command prepareEvent(String arguments) {
        Matcher matcher = EVENT_ARGS_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            throw new DukeException("Date/time not specified for event");
        }
        String desc = matcher.group("desc").trim();
        String dateString = matcher.group("date").trim();
        if (desc.length() == 0) {
            throw new DukeException("The description of an event cannot be empty");
        }
        LocalDate at;
        try {
            at = LocalDate.parse(dateString, Event.INPUT_DATE_FORMAT);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format");
        }
        return new AddEventCommand(desc, at);
    }

    private static Command prepareDeadline(String arguments) {
        Matcher matcher = DEADLINE_ARGS_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            throw new DukeException("Date/time not specified for deadline");
        }
        String desc = matcher.group("desc").trim();
        String dateString = matcher.group("date").trim();
        if (desc.length() == 0) {
            throw new DukeException("The description of a deadline cannot be empty");
        }
        LocalDate by;
        try {
            by = LocalDate.parse(dateString, Deadline.INPUT_DATE_FORMAT);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format");
        }
        return new AddDeadlineCommand(desc, by);
    }

    private static int parseTaskIndex(String taskIndexString) {
        if (taskIndexString == null) {
            throw new DukeException("Task index not specified");
        }
        taskIndexString = taskIndexString.trim();
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(taskIndexString);
        } catch (NumberFormatException e) {
            throw new DukeException(taskIndexString + " is not a valid task index");
        }
        return taskIndex;
    }

    private static Command prepareTodo(String arguments) {
        if (arguments == null) {
            throw new DukeException("The description of a todo cannot be empty");
        }
        String desc = arguments.trim();
        if (desc.length() == 0) {
            throw new DukeException("The description of a todo cannot be empty");
        }
        return new AddTodoCommand(desc);
    }
}
