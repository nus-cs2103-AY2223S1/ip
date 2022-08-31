package duke.parser;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.common.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Interprets user input and creating the corresponding command.
 *
 * @author Tan Jun Wei
 */
public abstract class Parser {
    /**
     * Parses the user input and creates the corresponding command.
     *
     * @param input String representing the user's input.
     * @return The command corresponding to the user's input.
     * @throws DukeException If the command is invalid.
     */
    public static Command parse(String input) throws DukeException {
        String[] commandArgs = input.trim().split(" ", 2);
        String commandType = commandArgs[0];
        Command command;
        switch (commandType) {
        case "list":
            command = new ListCommand();
            break;
        case "bye":
            command = new ExitCommand();
            break;
        case "mark":
            if (commandArgs.length < 2) {
                throw new DukeException("Please specify a task to mark.");
            }
            try {
                command = new MarkCommand(Integer.parseInt(commandArgs[1]));
            } catch (NumberFormatException e) {
                throw new DukeException("Please specify a valid number.");
            }
            break;
        case "unmark":
            if (commandArgs.length < 2) {
                throw new DukeException("Please specify a task to unmark.");
            }
            try {
                command = new UnmarkCommand(Integer.parseInt(commandArgs[1]));
            } catch (NumberFormatException e) {
                throw new DukeException("Please specify a valid number.");
            }
            break;
        case "delete":
            if (commandArgs.length < 2) {
                throw new DukeException("Please specify a task to delete.");
            }
            try {
                command = new DeleteCommand(Integer.parseInt(commandArgs[1]));
            } catch (NumberFormatException e) {
                throw new DukeException("Please specify a valid number.");
            }
            break;
        case "find":
            if (commandArgs.length < 2) {
                throw new DukeException("The search term cannot be empty.");
            }
            command = new FindCommand(commandArgs[1]);
            break;
        case "todo":
            if (commandArgs.length < 2) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            command = new AddCommand(new ToDo(commandArgs[1]));
            break;
        case "deadline":
            if (commandArgs.length < 2) {
                throw new DukeException("The description of a deadline cannot be empty.");
            }
            String[] deadlineArgs = commandArgs[1].split(" /by ");
            if (deadlineArgs.length < 2) {
                throw new DukeException("Please provide a description and deadline, separated by \"/by\".");
            }
            try {
                LocalDate date = LocalDate.parse(deadlineArgs[1]);
                command = new AddCommand(new Deadline(deadlineArgs[0], date));
            } catch (DateTimeParseException e) {
                throw new DukeException("Please provide a valid date. (YYYY-MM-DD)");
            }
            break;
        case "event":
            if (commandArgs.length < 2) {
                throw new DukeException("The description of a deadline cannot be empty.");
            }
            String[] eventArgs = commandArgs[1].split(" /at ");
            if (eventArgs.length < 2) {
                throw new DukeException("Please provide a description and date, separated by \"/at\".");
            }
            try {
                LocalDate date = LocalDate.parse(eventArgs[1]);
                command = new AddCommand(new Event(eventArgs[0], date));
            } catch (DateTimeParseException e) {
                throw new DukeException("Please provide a valid date. (YYYY-MM-DD)");
            }
            break;
        default:
            throw new DukeException("Please specify a valid command.");
        }
        return command;
    }
}
