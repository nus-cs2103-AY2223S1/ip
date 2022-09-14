package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.command.AddCommand;
import duke.command.CloneCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.GreetCommand;
import duke.command.IdleCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Encapsulation of parsing user inputs and records in the file.
 *
 * @author Sun Ruoxin
 */
public class Parser {

    /**
     * Parses user input to command.
     *
     * @param userInput the input to be parsed
     * @return the parsed command
     */
    public static Command parse(String userInput) {
        String[] substrings = userInput.split(" ", 2);
        String command = substrings[0];
        switch (command) {
        case "greet":
            return parseGreetCommand();
        case "bye":
            return parseExitCommand();
        case "list":
            return parseListCommand();
        case "mark":
            return parseMarkCommand(substrings);
        case "unmark":
            return parseUnmarkCommand(substrings);
        case "delete":
            return parseDeleteCommand(substrings);
        case "find":
            return parseFindCommand(substrings);
        case "clone":
            return parseCloneCommand(substrings);
        default:
            return parseAddCommand(substrings);
        }
    }

    /**
     * Parses the user input to a greet command.
     *
     * @return the greet command
     */
    public static Command parseGreetCommand() {
        return new GreetCommand();
    }

    /**
     * Parses the user input to an exit command.
     *
     * @return the exit command
     */
    public static Command parseExitCommand() {
        return new ExitCommand();
    }

    /**
     * Parses the user input to a list command.
     *
     * @return the list command
     */
    public static Command parseListCommand() {
        return new ListCommand();
    }

    /**
     * Parses the user input to an add command.
     *
     * @param substrings substrings of the user input
     * @return the add command
     */
    public static Command parseAddCommand(String[] substrings) {
        boolean isSingleWordCommand = substrings.length == 1;
        if (isSingleWordCommand) {
            throw new DukeException("The description of a task cannot be empty.");
        }
        String task = substrings[0];
        String description = substrings[1].split("/")[0].trim();
        switch (task) {
        case "todo":
            Todo todo = new Todo(description);
            return new AddCommand(todo);
        case "deadline":
            String by = substrings[1].split("/")[1].split(" ", 2)[1];
            Deadline deadline = new Deadline(description, by);
            return new AddCommand(deadline);
        case "event":
            String at = substrings[1].split("/")[1].split(" ", 2)[1];
            Event event = new Event(description, at);
            return new AddCommand(event);
        default:
            return new IdleCommand();
        }
    }

    /**
     * Parses the user input to a clone command.
     *
     * @param substrings substrings of the user input
     * @return the clone command
     */
    public static Command parseCloneCommand(String[] substrings) {
        boolean isSingleWordCommand = substrings.length == 1;
        if (isSingleWordCommand) {
            throw new DukeException("Please specify which task to clone.");
        }
        int index = Integer.parseInt(substrings[1]) - 1;
        return new CloneCommand(index);
    }

    /**
     * Parses the user input to a find command.
     *
     * @param substrings substrings of the user input
     * @return the find command
     */
    public static Command parseFindCommand(String[] substrings) {
        boolean isSingleWordCommand = substrings.length == 1;
        if (isSingleWordCommand) {
            throw new DukeException("Please specify what keyword to search for.");
        }
        String keyword = substrings[1];
        return new FindCommand(keyword);
    }

    /**
     * Parses the user input to a delete command.
     *
     * @param substrings substrings of the user input
     * @return the delete command
     */
    public static Command parseDeleteCommand(String[] substrings) {
        boolean isSingleWordCommand = substrings.length == 1;
        if (isSingleWordCommand) {
            throw new DukeException("Please specify which task to delete.");
        }
        int index = Integer.parseInt(substrings[1]) - 1;
        return new DeleteCommand(index);
    }

    /**
     * Parses the user input ot an unmark command.
     *
     * @param substrings substrings of the user input
     * @return the unmark command
     */
    public static Command parseUnmarkCommand(String[] substrings) {
        boolean isSingleWordCommand = substrings.length == 1;
        if (isSingleWordCommand) {
            throw new DukeException("Please specify which task to unmark.");
        }
        int index = Integer.parseInt(substrings[1]) - 1;
        return new UnmarkCommand(index);
    }

    /**
     * Parses the user input to a mark command.
     *
     * @param substrings substrings of the user input
     * @return the mark command
     */
    public static Command parseMarkCommand(String[] substrings) {
        boolean isSingleWordCommand = substrings.length == 1;
        if (isSingleWordCommand) {
            throw new DukeException("Please specify which task to mark.");
        }
        int index = Integer.parseInt(substrings[1]) - 1;
        return new MarkCommand(index);
    }

    /**
     * Parses string record in the file to task to be stored.
     *
     * @param taskRecord the string record in the file
     * @return the task to be stored
     */
    public static Task toTask(String taskRecord) {
        if (taskRecord.startsWith("T")) {
            // the task is a todo
            String description = taskRecord.split("\\|", 3)[2].trim();
            int status = Integer.parseInt(taskRecord.split("\\|", 3)[1].trim());
            return new Todo(description, checkStatus(status));
        } else if (taskRecord.startsWith("D")) {
            // the task is a deadline
            String description = taskRecord.split("\\|", 4)[2].trim();
            int status = Integer.parseInt(taskRecord.split("\\|", 4)[2].trim());
            String by = taskRecord.split("\\|", 4)[3].trim();
            return new Deadline(description, checkStatus(status), parseDate(by));
        } else {
            // the task is an event
            String description = taskRecord.split("\\|", 4)[2].trim();
            int status = Integer.parseInt(taskRecord.split("\\|", 4)[2].trim());
            String at = taskRecord.split("\\|", 4)[3].trim();
            return new Event(description, checkStatus(status), at);
        }
    }

    /**
     * Checks whether the Task recorded in the file is marked as done.
     *
     * @param status integer representing the status of the Task
     * @return a boolean value representing whether the Task is marked as done
     */
    public static boolean checkStatus(int status) {
        final int done = 1;
        return status == done;
    }

    /**
     * Parses the String record in the file to LocalDate to be stored.
     *
     * @param date the String in the record representing a date
     * @return the LocalDate to be stored in Task
     */
    public static LocalDate parseDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
