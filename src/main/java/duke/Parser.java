package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.ToDoCommand;
import duke.command.UnmarkCommand;

/**
 * Parser deals with making sense of the user command.
 *
 * @author Samsation
 * @version CS2103T AY 22/23 Sem 1
 *
 */

public class Parser {

    private enum CommandTag {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND
    }

    /**
     * A method that creates a new Command according to the user input.
     *
     * @param inputCommand Input command from the user.
     * @return A specific-typed Command corresponding to the user input.
     * @throws DukeException If the user input is unrecognised.
     */
    public static Command parse(String inputCommand) throws DukeException {
        try {
            String[] splitInputCommand = inputCommand.trim().split(" ", 2);
            CommandTag ct = CommandTag.valueOf(splitInputCommand[0].toUpperCase());
            switch (ct) {
            case BYE:
                return new ByeCommand();
            case LIST:
                return new ListCommand();
            case MARK:
                if (splitInputCommand.length == 1) {
                    throw new DukeException("\tPlease enter the task number to mark!");
                } else {
                    return new MarkCommand(Integer.parseInt(splitInputCommand[1].trim()) - 1);
                }
            case UNMARK:
                if (splitInputCommand.length == 1) {
                    throw new DukeException("\tPlease enter the task number to unmark!");
                } else {
                    return new UnmarkCommand(Integer.parseInt(splitInputCommand[1].trim()) - 1);
                }
            case TODO:
                if (splitInputCommand.length == 1) {
                    throw new DukeException("\tOOPS!!! The description of a todo cannot be empty.");
                } else {
                    return new ToDoCommand(splitInputCommand[1]);
                }
            case DEADLINE:
                if (splitInputCommand.length == 1) {
                    throw new DukeException("\tOOPS!!! The description of a deadline cannot be empty.");
                } else {
                    String[] splitDescriptionArray = splitInputCommand[1].split(" /by ");
                    if (splitDescriptionArray.length == 1) {
                        throw new DukeException("\tOOPS!!! The date of a deadline cannot be empty.");
                    }
                    return new DeadlineCommand(splitDescriptionArray[0], LocalDate.parse(splitDescriptionArray[1]));
                }
            case EVENT:
                if (splitInputCommand.length == 1) {
                    throw new DukeException("\tOOPS!!! The description of an event cannot be empty.");
                } else {
                    String[] splitDescriptionArray = splitInputCommand[1].split(" /at ");
                    if (splitDescriptionArray.length == 1) {
                        throw new DukeException("\tOOPS!!! The date of an event cannot be empty.");
                    }
                    return new EventCommand(splitDescriptionArray[0], LocalDate.parse(splitDescriptionArray[1]));
                }
            case DELETE:
                if (splitInputCommand.length == 1) {
                    throw new DukeException("\tPlease enter the task number to delete!");
                } else {
                    return new DeleteCommand(Integer.parseInt(splitInputCommand[1].trim()) - 1);
                }
            case FIND:
                if (splitInputCommand.length == 1) {
                    throw new DukeException("\tPlease enter a keyword!");
                } else {
                    return new FindCommand(splitInputCommand[1]);
                }
            default:
                throw new DukeException("\tERROR...ERROR...WHAT ARE YOU DOING HERE?!");
            }
        } catch (IllegalArgumentException e) {
            throw new DukeException("\tOOPS!!! I'm sorry, but I don't know what that means :-(");
        } catch (DateTimeParseException e) {
            throw new DukeException("\tOOPS!!! Please enter your date in the format yyyy-mm-dd!");
        }
    }
}
