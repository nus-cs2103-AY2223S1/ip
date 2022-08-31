package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.ToDoCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeEmptyDeadlineException;
import duke.exception.DukeEmptyEventException;
import duke.exception.DukeEmptyToDoException;
import duke.exception.DukeException;
import duke.exception.DukeInvalidCommandException;

/**
 * The Parser class that parses user commands.
 *
 * CS2103T IP
 * AY22/23 Semester 1
 * @author Tan Jia Rong
 */
public class Parser {
    /**
     * Executes user inputs.
     *
     * @param command Task/Action to be executed.
     * @param description Description of the Task/Action to be executed.
     */
    public static Command parse(String command, String description) throws DukeException {
        try {
            command = command.toLowerCase();

            int index;
            String[] input;
            String details;
            DateTimeFormatter formatter;

            switch (command) {
            case "list":
                return new ListCommand();
                // No need for break since it is unreachable
            case "mark":
                index = Integer.parseInt(description.substring(1)) - 1;
                return new MarkCommand(index);
                // No need for break since it is unreachable
            case "unmark":
                index = Integer.parseInt(description.substring(1)) - 1;
                return new UnmarkCommand(index);
                // No need for break since it is unreachable
            case "delete":
                index = Integer.parseInt(description.substring(1)) - 1;
                return new DeleteCommand(index);
                // No need for break since it is unreachable
            case "todo":
                if (description.isEmpty()) {
                    throw new DukeEmptyToDoException();
                }
                return new ToDoCommand(description);
                // No need for break since it is unreachable
            case "deadline":
                if (description.isEmpty()) {
                    throw new DukeEmptyDeadlineException();
                }
                if (!description.contains(" /by ")) {
                    throw new DukeInvalidCommandException();
                }
                input = description.split(" /by ");
                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                details = input[0];
                LocalDateTime by = LocalDateTime.parse(input[1], formatter);
                return new DeadlineCommand(details, by);
                // No need for break since it is unreachable
            case "event":
                if (description.isEmpty()) {
                    throw new DukeEmptyEventException();
                }
                if (!description.contains(" /at ")) {
                    throw new DukeInvalidCommandException();
                }
                input = description.split(" /at ");
                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                details = input[0];
                LocalDateTime at = LocalDateTime.parse(input[1], formatter);
                return new EventCommand(details, at);
                // No need for break since it is unreachable
            case "find":
                return new FindCommand(description);
                // No need for break since it is unreachable
            case "help":
                return new HelpCommand();
                // No need for break since it is unreachable
            case "bye":
                return new ExitCommand();
            default:
                throw new DukeInvalidCommandException();
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Please format date and time in YYYY-MM-DD hhmm.");
        }
    }
}
