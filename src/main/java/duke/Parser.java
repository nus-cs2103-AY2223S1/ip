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
import duke.command.UpdateCommand;

/**
 * Parser deals with making sense of the user command.
 *
 * @author Samsation
 * @version CS2103T AY 22/23 Sem 1
 *
 */

public class Parser {

    private enum CommandTag {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND, UPDATE
    }

    /**
     * Creates a new Command according to the user input.
     *
     * @param inputCommand Input command from the user.
     * @return A specific-typed Command corresponding to the user input.
     * @throws DukeException If the user input is unrecognised.
     */
    public static Command parse(String inputCommand) throws DukeException {
        try {
            String[] splitInputCommand = inputCommand.trim().split(" ", 2);
            assert splitInputCommand.length > 0 : "Input command cannot be empty!";
            CommandTag commandTag = CommandTag.valueOf(splitInputCommand[0].toUpperCase());
            switch (commandTag) {
            case BYE:
                return new ByeCommand();
            case LIST:
                return new ListCommand();
            case MARK:
                if (splitInputCommand.length == 1) {
                    throw new DukeException("Please enter the task number to mark!");
                } else {
                    return new MarkCommand(Integer.parseInt(splitInputCommand[1].trim()) - 1);
                }
            case UNMARK:
                if (splitInputCommand.length == 1) {
                    throw new DukeException("Please enter the task number to unmark!");
                } else {
                    return new UnmarkCommand(Integer.parseInt(splitInputCommand[1].trim()) - 1);
                }
            case TODO:
                if (splitInputCommand.length == 1) {
                    throw new DukeException("OOPS!!! The description of a todo \ncannot be empty.");
                } else {
                    return new ToDoCommand(splitInputCommand[1]);
                }
            case DEADLINE:
                if (splitInputCommand.length == 1) {
                    throw new DukeException("OOPS!!! The description of a deadline \ncannot be empty.");
                } else {
                    String[] splitDescriptionArray = splitInputCommand[1].split(" /by ");
                    if (splitDescriptionArray.length == 1) {
                        throw new DukeException("OOPS!!! The date of a deadline \ncannot be empty.");
                    }
                    return new DeadlineCommand(splitDescriptionArray[0], LocalDate.parse(splitDescriptionArray[1]));
                }
            case EVENT:
                if (splitInputCommand.length == 1) {
                    throw new DukeException("OOPS!!! The description of an event \ncannot be empty.");
                } else {
                    String[] splitDescriptionArray = splitInputCommand[1].split(" /at ");
                    if (splitDescriptionArray.length == 1) {
                        throw new DukeException("OOPS!!! The date of an event \ncannot be empty.");
                    }
                    return new EventCommand(splitDescriptionArray[0], LocalDate.parse(splitDescriptionArray[1]));
                }
            case DELETE:
                if (splitInputCommand.length == 1) {
                    throw new DukeException("Please enter the task number to delete!");
                } else {
                    return new DeleteCommand(Integer.parseInt(splitInputCommand[1].trim()) - 1);
                }
            case FIND:
                if (splitInputCommand.length == 1) {
                    throw new DukeException("Please enter a keyword!");
                } else {
                    return new FindCommand(splitInputCommand[1].split(" "));
                }
            case UPDATE:
                if (splitInputCommand.length == 1) {
                    throw new DukeException("Please enter the task number to update!");
                } else {
                    String[] splitDescriptionArray = splitInputCommand[1].split(" /to ");
                    if (splitDescriptionArray.length == 1) {
                        throw new DukeException("OOPS!!! The new date for the task \ncannot be empty.");
                    }
                    return new UpdateCommand(Integer.parseInt(splitDescriptionArray[0]) - 1 ,
                            LocalDate.parse(splitDescriptionArray[1]));
                }
            default:
                throw new DukeException("ERROR...ERROR...WHAT ARE YOU DOING HERE?!");
            }
        } catch (IllegalArgumentException e) {
            throw new DukeException("OOPS!!! I'm sorry, \nbut I don't know what that means :-(");
        } catch (DateTimeParseException e) {
            throw new DukeException("OOPS!!! Please enter your date \nin the format yyyy-mm-dd!");
        }
    }
}
