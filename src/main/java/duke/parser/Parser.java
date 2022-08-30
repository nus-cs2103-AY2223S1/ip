package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.EventCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.InvalidCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.TasksCommand;
import duke.commands.TodoCommand;
import duke.commands.UnmarkCommand;
import duke.data.exception.DukeException;

/**
 * This class encapsulates the Parser
 */
public class Parser {

    /**
     * Checks if the input is valid
     * @param input The input provided by the user
     * @return A Command to be executed
     * @throws DukeException If the input is invalid
     */
    public static Command parse(String input) throws DukeException {
        String[] components = input.split(" ", 2);
        switch (components[0]) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            checkInput(components);
            return new MarkCommand(Integer.parseInt(components[1]));
        case "unmark":
            checkInput(components);
            return new UnmarkCommand(Integer.parseInt(components[1]));
        case "todo":
            checkInput(components);
            return new TodoCommand(components[1]);
        case "deadline":
            checkInput(components);
            String[] deadlineInfo = components[1].split(" /by ");
            checkInput(deadlineInfo);
            checkDate(deadlineInfo[1]);
            return new DeadlineCommand(deadlineInfo[0], deadlineInfo[1]);
        case "event":
            checkInput(components);
            String[] eventInfo = components[1].split(" /at ");
            checkInput(eventInfo);
            checkDate(eventInfo[1]);
            return new EventCommand(eventInfo[0], eventInfo[1]);
        case "delete":
            checkInput(components);
            return new DeleteCommand(Integer.parseInt(components[1]));
        case "tasks":
            checkInput(components);
            checkDate(components[1]);
            return new TasksCommand(components[1]);
        case "find":
            checkInput(components);
            return new FindCommand(components[1].split(" "));
        default:
            return new InvalidCommand();
        }
    }

    /**
     * Checks if the input is valid
     * @param input An array consisting of the command and task
     * @throws DukeException If the array has a length of less than two
     */
    public static void checkInput(String[] input) throws DukeException {
        if (input.length <= 1) {
            throw new DukeException("Invalid input!");
        }
    }

    /**
     * Checks if the date has the required format
     * @param date The date of the task
     * @throws DukeException If the date does not follow the required format
     */
    public static void checkDate(String date) throws DukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("The date included should follow this format: dd/MM/yyyy");
        }
    }
}
