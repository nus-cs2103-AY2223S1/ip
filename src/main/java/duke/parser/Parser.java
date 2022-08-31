package duke.parser;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.common.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public abstract class Parser {

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
                throw new DukeException("\tOOPS!!! Please specify a task to mark.");
            }
            try {
                command = new MarkCommand(Integer.parseInt(commandArgs[1]));
            } catch (NumberFormatException e) {
                throw new DukeException("\tOOPS!!! Please specify a valid number.");
            }
            break;
        case "unmark":
            if (commandArgs.length < 2) {
                throw new DukeException("\tOOPS!!! Please specify a task to mark.");
            }
            try {
                command = new UnmarkCommand(Integer.parseInt(commandArgs[1]));
            } catch (NumberFormatException e) {
                throw new DukeException("\tOOPS!!! Please specify a valid number.");
            }
            break;
        case "delete":
            if (commandArgs.length < 2) {
                throw new DukeException("\tOOPS!!! Please specify a task to mark.");
            }
            try {
                command = new DeleteCommand(Integer.parseInt(commandArgs[1]));
            } catch (NumberFormatException e) {
                throw new DukeException("\tOOPS!!! Please specify a valid number.");
            }
            break;
        case "todo":
            if (commandArgs.length < 2) {
                throw new DukeException("\tOOPS!!! The description of a todo cannot be empty.");
            }
            command = new AddCommand(new ToDo(commandArgs[1]));
            break;
        case "deadline":
            if (commandArgs.length < 2) {
                throw new DukeException("\tOOPS!!! The description of a deadline cannot be empty.");
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
                throw new DukeException("\tOOPS!!! The description of a deadline cannot be empty.");
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
            throw new DukeException("\tOOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return command;
    }
}
