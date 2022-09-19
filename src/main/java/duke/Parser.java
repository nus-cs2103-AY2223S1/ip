package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.HighCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.TodoCommand;
import duke.command.UnmarkCommand;

/**
 * Parser makes sense of user inputs.
 *
 * @author Jet Lee
 * @version CS2103T AY22/23 Sem 1
 */
public class Parser {
    private enum CommandType {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND, HIGH
    }

    /**
     * Returns a Command to be executed according to the user input.
     *
     * @param fullCommand User input.
     * @return Command corresponding to the user input.
     * @throws DukeException If the user input is invalid.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] commandSplit = fullCommand.strip().split("\\s+", 2);

        CommandType c;
        try {
            c = CommandType.valueOf(commandSplit[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DukeException("I'm sorry, but I don't know what that means.");
        }

        try {
            if (commandSplit.length == 1) {
                switch (c) {
                case LIST:
                    return new ListCommand();
                case MARK:
                    throw new EmptyIndexException("mark");
                case UNMARK:
                    throw new EmptyIndexException("unmark");
                case HIGH:
                    throw new EmptyIndexException("set to high priority");
                case TODO:
                    throw new EmptyDescriptionException("a todo");
                case DEADLINE:
                    throw new EmptyDescriptionException("a deadline");
                case EVENT:
                    throw new EmptyDescriptionException("an event");
                case DELETE:
                    throw new EmptyIndexException("delete");
                case FIND:
                    throw new DukeException("Please provide at least one keyword to find.");
                default:
                    return new ByeCommand();
                }
            } else {
                String info = commandSplit[1].strip();
                switch (c) {
                case MARK:
                    return new MarkCommand(Integer.parseInt(info) - 1);
                case UNMARK:
                    return new UnmarkCommand(Integer.parseInt(info) - 1);
                case HIGH:
                    return new HighCommand(Integer.parseInt(info) - 1);
                case TODO:
                    return new TodoCommand(info);
                case DEADLINE:
                    String[] deadlineSplit = info.split(" /by ");
                    if (deadlineSplit.length == 1) {
                        throw new DukeException("Please provide both a description and a time.");
                    }
                    return new DeadlineCommand(deadlineSplit[0], LocalDate.parse(deadlineSplit[1]));
                case EVENT:
                    String[] eventSplit = info.split(" /at ");
                    if (eventSplit.length == 1) {
                        throw new DukeException("Please provide both a description and a time.");
                    }
                    return new EventCommand(eventSplit[0], LocalDate.parse(eventSplit[1]));
                case DELETE:
                    return new DeleteCommand(Integer.parseInt(info) - 1);
                case FIND:
                    String[] findSplit = info.split("\\s+");
                    return new FindCommand(findSplit);
                default:
                    throw new DukeException("Please re-enter the command only.");
                }
            }
        } catch (NumberFormatException e) {
            throw new InvalidIndexException();
        } catch (DateTimeParseException e) {
            throw new DukeException("Please provide a date in the format yyyy-mm-dd.");
        }
    }
}
