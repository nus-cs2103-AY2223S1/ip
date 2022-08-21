package duke;

import java.time.LocalDate;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.TaskOnDateCommand;
import duke.command.UnmarkCommand;

/**
 * Represents all command parsing logic.
 */
public class Parser {
    /**
     * Parse user command into Command instance to execute.
     * @param fullCommand String representing the command input of the user.
     * @return Command to be executed.
     * @throws DukeException If the fullCommand is not valid.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] inputArray = fullCommand.split(" +", 2);
        String firstWord = inputArray[0];
        String argsString = "";
        if (inputArray.length == 2) {
            argsString = inputArray[1];
        }

        Command resultCommand;
        // commands
        switch (firstWord) {
        case "bye":
            resultCommand = new ExitCommand();
            break;
        case "list":
            resultCommand = new ListCommand();
            break;
        case "mark":
            resultCommand = new MarkCommand(parseInt(argsString));
            break;
        case "unmark":
            resultCommand = new UnmarkCommand(parseInt(argsString));
            break;
        case "todo":
            resultCommand = new AddCommand(parseTodo(argsString));
            break;
        case "deadline":
            resultCommand = new AddCommand(parseDeadline(argsString));
            break;
        case "event":
            resultCommand = new AddCommand(parseEvent(argsString));
            break;
        case "delete":
            resultCommand = new DeleteCommand(parseInt(argsString));
            break;
        case "on":
            resultCommand = new TaskOnDateCommand(LocalDate.parse(argsString));
            break;
        case "find":
            resultCommand = new FindCommand(argsString);
            break;
        default:
            throw new DukeException("I don't know this command!");
        }

        return resultCommand;
    }

    /**
     * Parse description into Todo instance
     * @param description Description of Todo
     * @return Todo instance
     * @throws DukeException If description is empty.
     */
    public static Todo parseTodo(String description) throws DukeException {
        if (description.length() == 0) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        return new Todo(description);
    }

    /**
     * Parse argsString into Deadline instance
     * @param argsString String of format "description /by date"
     * @return Deadline instance.
     * @throws DukeException If argsString format is wrong.
     */
    public static Deadline parseDeadline(String argsString) throws DukeException {
        String[] args = argsString.split(" */by *");
        if (args.length != 2) {
            throw new DukeException("Wrong usage of deadline.\nUsage: deadline some description /by some date");
        }
        String description = args[0];
        String by = args[1];
        LocalDate byDate = LocalDate.parse(by);
        if (description.length() == 0) {
            throw new DukeException("Description should not be empty.");
        }

        return new Deadline(description, byDate);
    }

    /**
     * Parse argsString into Event instance.
     * @param argsString String of format "description /at date"
     * @return Event instance
     * @throws DukeException If argsString format is wrong.
     */
    public static Event parseEvent(String argsString) throws DukeException {
        String[] args = argsString.split(" */at *");
        if (args.length != 2) {
            throw new DukeException("Wrong usage of event.\nUsage: event some description /at some date");
        }
        String description = args[0];
        if (description.length() == 0) {
            throw new DukeException("Description should not be empty.");
        }
        String at = args[1];
        LocalDate atDate = LocalDate.parse(at);
        return new Event(description, atDate);
    }

    /**
     * Parse String to Integer
     * @param number String representation of integer.
     * @return Integer with value integer.
     * @throws DukeException If number is not of number format.
     */
    public static int parseInt(String number) throws DukeException {
        try {
            return Integer.parseInt(number);
        } catch (java.lang.NumberFormatException e) {
            throw new DukeException(String.format("%s is not a number. e.g 5 is a number, five is a string.", number));
        }
    }
}
