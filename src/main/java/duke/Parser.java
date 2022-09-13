package duke;

import java.util.Arrays;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.NoteCommand;
import duke.command.TodoCommand;
import duke.exception.DukeException;
import duke.types.ListObject;

/**
 * Parser takes in a string and returns a Command object which
 * represents the command the user wants to execute.
 */
public class Parser {
    /**
     * Parses a string and returns a Command object.
     *
     * @param input the command string
     * @return the Command object representing the command the user wants to execute
     * @throws DukeException if the command is invalid
     */
    public static Command parse(String input) throws DukeException {
        String[] inputSplit = input.split(" ");
        switch (inputSplit[0]) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "mark":
        case "unmark":
            return parseMarkUnmark(inputSplit);
        case "note":
            return parseNote(input, inputSplit);
        case "todo":
            // Error handling
            if (inputSplit.length < 2) {
                throw new DukeException("Please provide a description for your todo.");
            }
            // Return the command
            return new TodoCommand(input.substring(5));
        case "deadline":
        case "event":
            return parseDeadlineEvent(input, inputSplit);
        case "delete":
            return parseDelete(inputSplit);
        case "find":
            return parseFind(input, inputSplit);
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private static MarkCommand parseMarkUnmark(String[] inputSplit) throws DukeException {
        // Error handling
        if (inputSplit.length < 2) {
            throw new DukeException(
                    "You did not specify what task number to mark as done. Unable to mark task.");
        } else if (!isNumeric(inputSplit[1])) {
            throw new DukeException(
                    String.format("Invalid task number provided: %s. Unable to mark task.",
                            inputSplit[1]));
        }
        // Return the command
        return new MarkCommand(Integer.parseInt(inputSplit[1]) - 1,
                inputSplit[0].equals("mark") ? true : false);
    }

    private static NoteCommand parseNote(String input, String[] inputSplit) throws DukeException {
        // Error handling
        if (inputSplit.length < 2) {
            throw new DukeException("Please provide a description for your new note.");
        }
        // Return the command
        return new NoteCommand(input.substring(5));
    }

    private static Command parseDeadlineEvent(String input, String[] inputSplit) throws DukeException {
        String[] inputSlashSplit = input.split("/");
        // Error handling
        if (inputSlashSplit.length < 2 || inputSlashSplit[1].split(" ").length < 2) {
            throw new DukeException(String.format(
                    "Please specify a time for your %s.", inputSplit[0]));
        }
        if (inputSlashSplit[0].split(" ").length < 2) {
            throw new DukeException(String.format(
                    "Please provide a description for your %s.", inputSplit[0]));
        }
        // Return the commands
        String date = input.split("/")[1];
        String[] description = inputSlashSplit[0].split(" ");
        if (inputSplit[0].equals("deadline")) {
            return new DeadlineCommand(String.join(
                    " ", Arrays.copyOfRange(description, 1, description.length)), date);
        } else {
            return new EventCommand(String.join(
                    " ", Arrays.copyOfRange(description, 1, description.length)), date);
        }
    }

    private static DeleteCommand parseDelete(String[] inputSplit) throws DukeException {
        ListObject type = ListObject.TASK;
        // Error handling
        if (inputSplit.length < 2) {
            throw new DukeException("You did not specify what number to delete.");
        }
        if (inputSplit[1].startsWith("N")) {
            type = ListObject.NOTE;
            inputSplit[1] = inputSplit[1].substring(1);
        }
        if (!isNumeric(inputSplit[1])) {
            throw new DukeException(String.format(
                    "Invalid %s number provided: %s. Unable to delete %s.", type.label, inputSplit[1], type.label));
        }
        // Return the command
        return new DeleteCommand(type, Integer.parseInt(inputSplit[1]) - 1);
    }

    private static FindCommand parseFind(String input, String[] inputSplit) throws DukeException {
        // Error handling
        if (inputSplit.length < 2) {
            throw new DukeException("Please provide a search term.");
        }
        // Return the command
        return new FindCommand(input.substring(5));
    }

    private static boolean isNumeric(String str) {
        return str.matches("\\d+(\\.\\d+)?");
    }
}
