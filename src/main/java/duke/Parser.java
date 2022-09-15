package duke;

import java.time.format.DateTimeParseException;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EditDescCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.TodoCommand;

import duke.exception.InvalidDescriptionException;
import duke.exception.InvalidIndexException;
import duke.exception.InvalidInputException;
import duke.exception.InvalidTimeException;

/**
 * Class that parses user inputs in Duke to return an appropriate command object
 */
public class Parser {

    /**
     * Parses user input and returns a command
     */
    public static Command parse(String fullCommand) throws InvalidInputException, InvalidIndexException,
            DateTimeParseException, InvalidDescriptionException, InvalidTimeException {

        fullCommand += " ";
        String command = fullCommand.substring(0, fullCommand.indexOf(' ')).trim();
        int timeIndex;
        int descIndex;
        int targetIndex;

        switch (command) {
        case "list":
            return new ListCommand();

        case "unmark":
            if (fullCommand.trim().length() <= 6) {
                throw new InvalidIndexException();
            }

            targetIndex = Integer.parseInt(fullCommand.substring(6).trim()) - 1;
            return new MarkCommand(false, targetIndex);

        case "mark":
            if (fullCommand.trim().length() <= 4) {
                throw new InvalidIndexException();
            }

            targetIndex = Integer.parseInt(fullCommand.substring(4).trim()) - 1;
            return new MarkCommand(true, targetIndex);

        case "delete":
            if (fullCommand.trim().length() <= 6) {
                throw new InvalidIndexException();
            }

            targetIndex = Integer.parseInt(fullCommand.substring(6).trim()) - 1;
            return new DeleteCommand(targetIndex);

        case "find":
            return new FindCommand(fullCommand.substring(4).trim());

        case "todo":
            return new TodoCommand(fullCommand.substring(4).trim());

        case "editd":
            if (fullCommand.trim().length() <= 5) {
                throw new InvalidIndexException();
            }

            targetIndex = Integer.parseInt(fullCommand.substring(5).trim().substring(0, 1));
            descIndex = fullCommand.indexOf("/d");
            if (descIndex == -1) {
                throw new InvalidDescriptionException();
            }
            return new EditDescCommand(targetIndex - 1, fullCommand.substring(descIndex + 2).trim());

        case "deadline":
            timeIndex = fullCommand.indexOf("/by");
            if (timeIndex == -1) {
                throw new InvalidTimeException();
            }
            return new DeadlineCommand(fullCommand.substring(8, timeIndex - 1).trim(),
                    fullCommand.substring(timeIndex + 3).trim());

        case "event":
            timeIndex = fullCommand.indexOf("/at");
            if (timeIndex == -1) {
                throw new InvalidTimeException();
            }
            return new EventCommand(fullCommand.substring(5, timeIndex - 1).trim(),
                    fullCommand.substring(timeIndex + 3).trim());

        default:
            throw new InvalidInputException();
        }
    }
}
