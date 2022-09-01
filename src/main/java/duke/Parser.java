package duke;

import java.time.LocalDate;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.TodoCommand;
import duke.command.UnmarkCommand;

/**
 * Parses the user input and executes the corresponding commands.
 */
public class Parser {
    /**
     * Returns Command object based on user's input.
     * @param fullCommand user's input.
     * @return specific command.
     * @throws DukeException If command does not exist.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] commandSegments = fullCommand.split(" ", 2);
        String mainCommand = commandSegments[0].toLowerCase().trim();

        switch (mainCommand) {
        case "list":
            return new ListCommand();
        case "bye":
            return new ByeCommand();
        case "todo":
            return new TodoCommand(commandSegments[1].trim());
        case "deadline":
            String[] deadlineSegments = commandSegments[1].split("/by", 2);
            return new DeadlineCommand(deadlineSegments[0], LocalDate.parse(deadlineSegments[1].trim()));
        case "event":
            String[] eventSegments = commandSegments[1].split("/at", 2);
            return new EventCommand(eventSegments[0], eventSegments[1]);
        case "mark":
            return new MarkCommand(Integer.parseInt(commandSegments[1].trim()));
        case "unmark":
            return new UnmarkCommand(Integer.parseInt(commandSegments[1].trim()));
        case "delete":
            return new DeleteCommand(Integer.parseInt(commandSegments[1].trim()));
        case "find":
            return new FindCommand(commandSegments[1].trim());
        default:
            throw new DukeException(mainCommand + "? I don't know what that means\n");
        }
    }
}
