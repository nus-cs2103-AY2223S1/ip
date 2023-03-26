package duke.main;

import java.time.LocalDate;

import duke.command.Command;
import duke.command.DeleteTaskCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.InvalidCommand;
import duke.command.MakeDeadlineCommand;
import duke.command.MakeEventCommand;
import duke.command.MakeToDoCommand;
import duke.command.MarkTaskCommand;
import duke.command.ShowListCommand;
import duke.exception.MissingArgumentException;
import duke.exception.MissingDescriptionException;
import duke.exception.MissingIndexException;
import duke.exception.MissingTimeException;
import duke.task.Deadline;
import duke.task.Event;

/**
 * Class which parses the input by the user.
 */
public class Parser {

    /**
     * Parses the full command and generates a corresponding Command object which will be executed.
     * @param fullCommand The full command to be parsed.
     * @return A Command object which does what the command specifies when executed.
     * @throws MissingIndexException If the index is needed but missing.
     * @throws MissingDescriptionException If the description is needed but missing.
     * @throws MissingTimeException If the time is needed but missing.
     */
    public static Command parse(String fullCommand)
            throws MissingIndexException, MissingDescriptionException, MissingTimeException, MissingArgumentException {
        String[] splitCommand = fullCommand.split(" ", 2);
        switch (splitCommand[0]) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ShowListCommand();
        case "mark":
            return makeMarkTaskCommand(true, splitCommand);
        case "unmark":
            return makeMarkTaskCommand(false, splitCommand);
        case "todo":
            return makeMakeToDoTaskCommand(splitCommand);
        case "deadline":
            return makeMakeDeadlineCommand(splitCommand);
        case "event":
            return makeMakeEventCommand(splitCommand);
        case "delete":
            return makeDeleteTaskCommand(splitCommand);
        case "find":
            return makeFindCommand(splitCommand);
        default:
            return new InvalidCommand();
        }
    }

    /**
     * Helper method for making the MarkTaskCommand.
     * @param isDone Whether to mark or unmark the task.
     * @param command Split command.
     * @return MarkTaskCommand object that marks or unmarks a task when executed.
     * @throws MissingIndexException If the index is missing.
     */
    private static MarkTaskCommand makeMarkTaskCommand(boolean isDone, String[] command) throws MissingIndexException {
        if (command.length < 2) {
            throw new MissingIndexException("Missing index");
        }
        int index = Integer.parseInt(command[1]);
        return new MarkTaskCommand(isDone, index - 1);
    }

    /**
     * Helper method for making the DeleteTaskCommand.
     * @param command Split command.
     * @return DeleteTaskCommand object that deletes the task at index.
     * @throws MissingIndexException If the index is missing.
     */
    private static DeleteTaskCommand makeDeleteTaskCommand(String[] command) throws MissingIndexException {
        if (command.length < 2) {
            throw new MissingIndexException("Missing index");
        }
        int index = Integer.parseInt(command[1]);
        return new DeleteTaskCommand(index - 1);
    }

    /**
     * Helper method for making the MakeToDoCommand.
     * @param command Split command.
     * @return MakeToDoTaskCommand object that makes a ToDo object and adds it to a TaskList object when executed.
     * @throws MissingDescriptionException If the description is missing.
     */
    private static MakeToDoCommand makeMakeToDoTaskCommand(String[] command) throws MissingDescriptionException {
        if (command.length < 2) {
            throw new MissingDescriptionException("Missing description");
        }
        return new MakeToDoCommand(command[1]);
    }

    /**
     * Helper function for making a FindCommand object.
     * @param command Split command.
     * @return FindCommand object which shows Task objects matching a string when executed.
     * @throws MissingArgumentException If the argument is missing.
     */
    private static FindCommand makeFindCommand(String[] command) throws MissingArgumentException {
        if (command.length < 2) {
            throw new MissingArgumentException("Missing argument");
        }
        return new FindCommand(command[1]);
    }

    /**
     * Helper method for making the MakeDeadlineCommand.
     * @param command Split command.
     * @return MakeDeadlineCommand object that makes a Deadline object and adds it to a TaskList object when executed.
     * @throws MissingDescriptionException If the description is missing.
     * @throws MissingTimeException If the time is missing.
     */
    private static MakeDeadlineCommand makeMakeDeadlineCommand(String[] command)
            throws MissingDescriptionException, MissingTimeException {
        if (command.length < 2) {
            throw new MissingDescriptionException("Missing description");
        }
        String[] splitCommand = command[1].split(Deadline.DELIMITER, 2);
        if (splitCommand.length < 2) {
            throw new MissingTimeException("Missing time");
        }
        return new MakeDeadlineCommand(splitCommand[0], LocalDate.parse(splitCommand[1]));
    }

    /**
     * Helper method for making the MakeEventCommand.
     * @param command Split command.
     * @return MakeEventObject that makes an Event object and adds it to a TaskList object when executed.
     * @throws MissingDescriptionException If the description is missing.
     * @throws MissingTimeException If the time is missing.
     */
    private static MakeEventCommand makeMakeEventCommand(String[] command)
            throws MissingDescriptionException, MissingTimeException {
        if (command.length < 2) {
            throw new MissingDescriptionException("Missing description");
        }
        String[] splitCommand = command[1].split(Event.DELIMITER, 2);
        if (splitCommand.length < 2) {
            throw new MissingTimeException("Missing time");
        }
        return new MakeEventCommand(splitCommand[0], LocalDate.parse(splitCommand[1]));
    }
}
