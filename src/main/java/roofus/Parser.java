package roofus;

import java.time.LocalDate;

import roofus.command.ByeCommand;
import roofus.command.ClearCommand;
import roofus.command.Command;
import roofus.command.DeadlineCommand;
import roofus.command.DeleteCommand;
import roofus.command.EventCommand;
import roofus.command.FindCommand;
import roofus.command.ListCommand;
import roofus.command.MarkCommand;
import roofus.command.SortCommand;
import roofus.command.ToDoCommand;
import roofus.command.UnmarkCommand;
import roofus.task.Deadline;
import roofus.task.Event;
import roofus.task.ToDo;

/**
 * Represents a handler to convert user inputs
 * into commands that Roofus understands.
 */
public class Parser {
    /**
     * Represents a list of all valid commands.
     */
    private enum CommandList {
        BYE, LIST, TODO, EVENT, DEADLINE,
        MARK, UNMARK, DELETE, CLEAR, FIND, SORT
    }

    /**
     * Reads a user input and converts it
     * into a command.
     *
     * @param fullCommand String representing a user's command'.
     * @return Command
     * @throws RoofusException If command is invalid.
     * @see Command
     */
    public static Command parse(String fullCommand) throws RoofusException {
        String[] commandDetails = fullCommand.split(" ", 2);
        String firstWord = commandDetails[0];
        try {
            CommandList.valueOf(firstWord.toUpperCase());
        } catch (IllegalArgumentException err) {
            throw new RoofusException("\"" + commandDetails[0] + "\" is not a command!");
        }
        CommandList command = CommandList.valueOf(firstWord.toUpperCase());
        switch (command) {
        case BYE:
            return new ByeCommand();
        case LIST:
            return new ListCommand();
        case SORT:
            return new SortCommand();
        case DELETE:
            return parseDeleteCommand(commandDetails);
        case MARK:
            return parseMarkCommand(commandDetails);
        case UNMARK:
            return parseUnmarkCommand(commandDetails);
        case TODO:
            return parseTodoCommand(commandDetails);
        case DEADLINE:
            return parseDeadlineCommand(commandDetails);
        case EVENT:
            return parseEventCommand(commandDetails);
        case CLEAR:
            return new ClearCommand();
        case FIND:
            return parseFindCommand(commandDetails);
        default:
            assert false : command;
        }
        throw new RoofusException("Something went wrong, please restart");
    }

    /**
     * Checks if command details are in the correct format and throws exception otherwise.
     *
     * @param commandDetails
     * @return The DeleteCommand that the user has requested.
     * @throws RoofusException If command is wrongly formatted
     */
    public static Command parseDeleteCommand(String[] commandDetails) throws RoofusException {
        if (commandDetails.length < 2) {
            throw new RoofusException("Huh?! Delete what?");
        }
        try {
            int index = Integer.parseInt(commandDetails[1]);
            return new DeleteCommand(index);
        } catch (IllegalArgumentException err) {
            throw new RoofusException("Delete command is in wrong format!!");
        }
    }

    /**
     * Checks if command details are in the correct format and throws exception otherwise.
     *
     * @param commandDetails
     * @return The MarkCommand that the user has requested.
     * @throws RoofusException If command is wrongly formatted
     */
    public static Command parseMarkCommand(String[] commandDetails) throws RoofusException {
        if (commandDetails.length < 2) {
            throw new RoofusException("Huh?! Mark what?");
        }
        try {
            int index = Integer.parseInt(commandDetails[1]);
            return new MarkCommand(index);
        } catch (IllegalArgumentException err) {
            throw new RoofusException("Mark command is in wrong format!!");
        }
    }

    /**
     * Checks if command details are in the correct format and throws exception otherwise.
     *
     * @param commandDetails
     * @return The UnmarkCommand that the user has requested.
     * @throws RoofusException If command is wrongly formatted
     */
    public static Command parseUnmarkCommand(String[] commandDetails) throws RoofusException {
        if (commandDetails.length < 2) {
            throw new RoofusException("Huh?! Unmark what?");
        }
        try {
            int index = Integer.parseInt(commandDetails[1]);
            return new UnmarkCommand(index);
        } catch (IllegalArgumentException err) {
            throw new RoofusException("Unmark command is in wrong format!!");
        }
    }

    /**
     * Checks if command details are in the correct format and throws exception otherwise.
     *
     * @param commandDetails
     * @return The ToDoCommand that the user has requested.
     * @throws RoofusException If command is wrongly formatted
     */
    public static Command parseTodoCommand(String[] commandDetails) throws RoofusException {
        if (commandDetails.length < 2) {
            throw new RoofusException("To do what??");
        }
        return new ToDoCommand(new ToDo(commandDetails[1]));
    }

    /**
     * Checks if command details are in the correct format and throws exception otherwise.
     *
     * @param commandDetails
     * @return The DeadlineCommand that the user has requested.
     * @throws RoofusException If command is wrongly formatted
     */
    public static Command parseDeadlineCommand(String[] commandDetails) throws RoofusException {
        if (commandDetails.length < 2) {
            throw new RoofusException("What deadline are you talking about?");
        }
        String details = commandDetails[1];
        String[] separate = details.split(" /by ", 2);
        if (separate.length < 2) {
            throw new RoofusException("Wrong format for deadline!!");
        }
        try {
            LocalDate.parse(separate[1]);
            return new DeadlineCommand(new Deadline(separate[0], separate[1]));
        } catch (Exception err) {
            throw new RoofusException("Date is in wrong format!!!");
        }
    }

    /**
     * Checks if command details are in the correct format and throws exception otherwise.
     *
     * @param commandDetails
     * @return The EventCommand that the user has requested.
     * @throws RoofusException If command is wrongly formatted
     */
    public static Command parseEventCommand(String[] commandDetails) throws RoofusException {
        if (commandDetails.length < 2) {
            throw new RoofusException("What event are you talking about?");
        }
        String details = commandDetails[1];
        String[] separate = details.split(" /at ", 2);
        if (separate.length < 2) {
            throw new RoofusException("Wrong format for event!!");
        }
        try {
            LocalDate.parse(separate[1]);
            return new EventCommand(new Event(separate[0], separate[1]));
        } catch (Exception err) {
            throw new RoofusException("Date is in wrong format!!!");
        }
    }

    /**
     * Checks if command details are in the correct format and throws exception otherwise.
     *
     * @param commandDetails
     * @return The FindCommand that the user has requested.
     * @throws RoofusException If command is wrongly formatted
     */
    public static Command parseFindCommand(String[] commandDetails) throws RoofusException {
        if (commandDetails.length < 2 || commandDetails[1].isEmpty()) {
            throw new RoofusException("Huh?! Find what?");
        }
        return new FindCommand(commandDetails[1]);
    }
}
