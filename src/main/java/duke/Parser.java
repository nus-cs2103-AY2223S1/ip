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
            return parseGreetCommand(substrings);
        case "bye":
            return parseExitCommand(substrings);
        case "list":
            return parseListCommand(substrings);
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

    public static Command parseGreetCommand(String[] substrings) {
        return new GreetCommand();
    }

    public static Command parseExitCommand(String[] substrings) {
        return new ExitCommand();
    }

    public static Command parseListCommand(String[] substrings) {
        return new ListCommand();
    }

    public static Command parseAddCommand(String[] substrings) {
        boolean isSingleWordCommand = substrings.length == 1;
        String task = substrings[0];
        String description = substrings[1].split("/")[0].trim();
        if (isSingleWordCommand) {
            throw new DukeException("The description of a task cannot be empty.");
        }
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

    public static Command parseCloneCommand(String[] substrings) {
        boolean isSingleWordCommand = substrings.length == 1;
        // when user enters clone and a number
        // clone the corresponding task from the list
        if (isSingleWordCommand) {
            throw new DukeException("Please specify which task to clone.");
        }
        int index = Integer.parseInt(substrings[1]) - 1;
        return new CloneCommand(index);
    }

    public static Command parseFindCommand(String[] substrings) {
        boolean isSingleWordCommand = substrings.length == 1;
        // when user enters find and a keyword
        // search for that keyword in the list
        // and print out the result
        if (isSingleWordCommand) {
            throw new DukeException("Please specify what keyword to search for.");
        }
        String keyword = substrings[1];
        return new FindCommand(keyword);
    }

    public static Command parseDeleteCommand(String[] substrings) {
        boolean isSingleWordCommand = substrings.length == 1;
        // when user enters delete and a number
        // delete the corresponding task from the list
        if (isSingleWordCommand) {
            throw new DukeException("Please specify which task to delete.");
        }
        int index = Integer.parseInt(substrings[1]) - 1;
        return new DeleteCommand(index);
    }

    public static Command parseUnmarkCommand(String[] substrings) {
        boolean isSingleWordCommand = substrings.length == 1;

        // when user enters unmark and a number
        // mark the corresponding task as not done
        if (isSingleWordCommand) {
            throw new DukeException("Please specify which task to unmark.");
        }
        int index = Integer.parseInt(substrings[1]) - 1;
        return new UnmarkCommand(index);
    }

    public static Command parseMarkCommand(String[] substrings) {
        boolean isSingleWordCommand = substrings.length == 1;

        // when user enters mark and a number
        // mark the corresponding task as done
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
