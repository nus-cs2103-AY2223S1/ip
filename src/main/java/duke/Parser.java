package duke;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.NoCommand;
import duke.command.TodoCommand;
import duke.command.FindCommand;

import duke.exception.InvalidDescriptionException;
import duke.exception.InvalidIndexException;
import duke.exception.InvalidInputException;
import duke.exception.InvalidTimeException;

import java.time.format.DateTimeParseException;

/**
 * Class that parses user inputs in Duke to return an appropriate command object
 */
public class Parser {

    /**
     * Parse user input and returns a command
     */
    public static Command parse(String fullCommand) throws InvalidInputException, InvalidIndexException,
            DateTimeParseException, InvalidDescriptionException, InvalidTimeException {

        fullCommand += " ";
        String command = fullCommand.substring(0, fullCommand.indexOf(' ')).trim();

        switch (command) {
        case "list":
            return new ListCommand();

        case "unmark":
            return new MarkCommand(false,
                    Integer.parseInt(fullCommand.substring(6).trim()) - 1);

        case "mark":
            return new MarkCommand(true,
                    Integer.parseInt(fullCommand.substring(4).trim()) - 1);

        case "delete":
            return new DeleteCommand(Integer.parseInt(fullCommand.substring(6).trim()) - 1);

        case "find":
            return new FindCommand(fullCommand.substring(4).trim());

        case "todo":
            return new TodoCommand(fullCommand.substring(4).trim());

        case "deadline":
            try {
                int timeIndex = fullCommand.indexOf("/by");
                if (timeIndex == -1) {
                    throw new InvalidTimeException();
                }
                return new DeadlineCommand(fullCommand.substring(8, timeIndex - 1).trim(),
                        fullCommand.substring(timeIndex + 3).trim());
            } catch (InvalidTimeException e) {
                System.out.println(e.getMessage());
            }
            break;

        case "event":
            try {
                int timeIndex = fullCommand.indexOf("/at");
                if (timeIndex == -1) {
                    throw new InvalidTimeException();
                }
                return new EventCommand(fullCommand.substring(5, timeIndex - 1).trim(),
                        fullCommand.substring(timeIndex + 3).trim());
            } catch (InvalidTimeException e) {
                System.out.println(e.getMessage());
            }
            break;

        default:
            throw new InvalidInputException();
        }
        return new NoCommand();
    }
}
