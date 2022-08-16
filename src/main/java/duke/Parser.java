package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.TodoCommand;
import duke.command.UnmarkCommand;

public class Parser {
    private enum CommandType {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE
    }

    public static Command parse(String fullCommand) throws DukeException {
        String[] commandSplit = fullCommand.strip().split(" ", 2);

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
                case TODO:
                    throw new EmptyDescriptionException("a todo");
                case DEADLINE:
                    throw new EmptyDescriptionException("a deadline");
                case EVENT:
                    throw new EmptyDescriptionException("an event");
                case DELETE:
                    throw new EmptyIndexException("delete");
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
                default:
                    throw new DukeException("Please re-enter the command only.");
                }
            }
        } catch (NumberFormatException e) {
            throw new DukeException("The index provided is not a positive integer.");
        } catch (DateTimeParseException e) {
            throw new DukeException("Please provide a date in the format yyyy-mm-dd.");
        }
    }
}
