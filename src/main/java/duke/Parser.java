package duke;

import duke.command.*;

import java.time.LocalDate;

public class Parser {
    private enum CommandType {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE
    }

    public static Command parse(String fullCommand) throws DukeException {
        String[] commandSplit = fullCommand.trim().split(" ", 2);
        CommandType c = CommandType.valueOf(commandSplit[0].toUpperCase());

        if (commandSplit.length == 1) {
            switch (c) {
            case LIST:
                return new ListCommand();
            case MARK:
                throw new DukeException("The index to mark cannot be empty.");
            case UNMARK:
                throw new DukeException("The index to unmark cannot be empty.");
            case TODO:
                throw new DukeException("The description of a todo cannot be empty.");
            case DEADLINE:
                throw new DukeException("The description of a deadline cannot be empty.");
            case EVENT:
                throw new DukeException("The description of an event cannot be empty.");
            case DELETE:
                throw new DukeException("The index to delete cannot be empty.");
            default:
                return new ByeCommand();
            }
        } else {
            String info = commandSplit[1];
            switch (c) {
            case MARK:
                if (!info.matches("[0-9]+")) {
                    throw new DukeException("The index provided is not a positive integer.");
                }
                return new MarkCommand(Integer.parseInt(info));
            case UNMARK:
                if (!info.matches("[0-9]+")) {
                    throw new DukeException("The index provided is not a positive integer.");
                }
                return new UnmarkCommand(Integer.parseInt(info));
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
                if (!info.matches("[0-9]+")) {
                    throw new DukeException("The index provided is not a positive integer.");
                }
                return new DeleteCommand(Integer.parseInt(info));
            default:
                throw new DukeException("Please re-enter the command only.");
            }
        }
    }
}