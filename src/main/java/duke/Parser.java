package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * The Parser class deals with making sense of user commands to Duke.
 */
public class Parser {
    private enum TempCommand {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, ON, FIND
    }

    /**
     * Executes the user command specified to Duke.
     *
     * @param fullCommand The command specified to Duke.
     * @return A Command object representing the user command.
     * @throws DukeException when the user command is invalid.
     */
    public static Command parse(String fullCommand) throws DukeException {
        try {
            String[] strings = fullCommand.split(" ");
            String firstCommand = strings[0].toUpperCase();
            TempCommand tempCommand = TempCommand.valueOf(firstCommand);

            switch (tempCommand) {
            case BYE:
                return new ExitCommand();

            case LIST:
                return new ListCommand();

            case MARK:
                if (fullCommand.length() == 4) {
                    throw new DukeException("Specify which task to mark with a single integer.");
                }
                try {
                    String input = fullCommand.substring(5);
                    int n = Integer.parseInt(input);
                    return new MarkCommand(n);
                } catch (NumberFormatException e) {
                    throw new DukeException("Specify which task to mark with a single integer.");
                }

            case UNMARK:
                if (fullCommand.length() == 6) {
                    throw new DukeException("Specify which task to unmark with a single integer.");
                }
                try {
                    String input = fullCommand.substring(7);
                    int n = Integer.parseInt(input);
                    return new UnmarkCommand(n);
                } catch (NumberFormatException e) {
                    throw new DukeException("Specify which task to unmark with a single integer.");
                }

            case TODO:
                try {
                    return new AddCommand(new Todo(fullCommand.substring(5)));
                } catch (StringIndexOutOfBoundsException e) {
                    throw new DukeException("The description of a todo cannot be empty.");
                }

            case DEADLINE:
                try {
                    String input = fullCommand.substring(9);
                    if (input.startsWith("/by")) {
                        throw new DukeException("The description of a deadline cannot be empty.");
                    }
                    String[] splitInput = input.split(" /by ");
                    return new AddCommand(new Deadline(splitInput[0], splitInput[1]));
                } catch (StringIndexOutOfBoundsException e) {
                    throw new DukeException("The description of a deadline cannot be empty.");
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("Use /by to provide when a deadline must be completed.");
                }

            case EVENT:
                try {
                    String input = fullCommand.substring(6);
                    if (input.startsWith("/at")) {
                        throw new DukeException("The description of an event cannot be empty.");
                    }
                    String[] splitInput = input.split(" /at ");
                    return new AddCommand(new Event(splitInput[0], splitInput[1]));
                } catch (StringIndexOutOfBoundsException | DukeException e) {
                    throw new DukeException("The description of an event cannot be empty.");
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("Use /at to provide when an event occurs.");
                }

            case DELETE:
                if (fullCommand.length() == 6) {
                    throw new DukeException("Specify which task to delete with a single integer.");
                }
                try {
                    String input = fullCommand.substring(7);
                    int n = Integer.parseInt(input);
                    return new DeleteCommand(n);
                } catch (NumberFormatException e) {
                    throw new DukeException("Specify which task to delete with a single integer.");
                }

            case ON:
                if (fullCommand.length() == 2) {
                    throw new DukeException("Specify the date to check with yyyy-MM-dd.");
                }
                try {
                    LocalDate date = LocalDate.parse(fullCommand.substring(3));
                    return new OnCommand(date);
                } catch (DateTimeParseException e) {
                    throw new DukeException("Specify the date to check with yyyy-MM-dd.");
                }

            case FIND:
                try {
                    return new FindCommand(fullCommand.substring(5));
                } catch (StringIndexOutOfBoundsException e) {
                    throw new DukeException("Include the keyword you want to find.");
                }

            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (IllegalArgumentException e) {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
