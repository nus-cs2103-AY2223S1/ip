package duke.parser;


import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.stream.Stream;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnMarkCommand;
import duke.command.UndoCommand;
import duke.common.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

/**
 * Deals with parsing the user input.
 *
 * @author Rama Aryasuta Pangestu
 */
public abstract class Parser {
    private static Stream<String> parseArgument(String[] args, String argument) {
        return Arrays.stream(args)
                .dropWhile(x -> !x.equals(argument)).skip(1)
                .takeWhile(x -> x.charAt(0) != '/');
    }

    private static Command parseExitCommand(String input) {
        assert !input.isBlank();
        String[] args = input.trim().split("\\s+");
        assert args.length >= 1;
        assert args[0].equals("bye");
        return new ExitCommand();
    }

    private static Command parseListCommand(String input) {
        assert !input.isBlank();
        String[] args = input.trim().split("\\s+");
        assert args.length >= 1;
        assert args[0].equals("list");
        return new ListCommand();
    }

    private static Command parseUndoCommand(String input) {
        assert !input.isBlank();
        String[] args = input.trim().split("\\s+");
        assert args.length >= 1;
        assert args[0].equals("undo");
        return new UndoCommand();
    }

    private static Command parseFindCommand(String input) {
        assert !input.isBlank();
        String[] args = input.trim().split("\\s+");
        assert args.length >= 1;
        assert args[0].equals("find");
        return new FindCommand(Parser.parseArgument(args, "find")
                .reduce("", (x, y) -> x + " " + y).trim());
    }

    private static Command parseMarkCommand(String input) throws DukeException {
        assert !input.isBlank();
        String[] args = input.trim().split("\\s+");
        assert args.length >= 1;
        assert args[0].equals("mark");
        Command command;
        try {
            command = new MarkCommand(Integer.parseInt(args[1]) - 1);
        } catch (IndexOutOfBoundsException exception) {
            throw new DukeException("OOPS!!! No task index is specified :(");
        } catch (NumberFormatException exception) {
            throw new DukeException("OOPS!!! You didn't give a valid index :(");
        }
        return command;
    }

    private static Command parseUnMarkCommand(String input) throws DukeException {
        assert !input.isBlank();
        String[] args = input.trim().split("\\s+");
        assert args.length >= 1;
        assert args[0].equals("unmark");
        Command command;
        try {
            command = new UnMarkCommand(Integer.parseInt(args[1]) - 1);
        } catch (IndexOutOfBoundsException exception) {
            throw new DukeException("OOPS!!! No task index is specified :(");
        } catch (NumberFormatException exception) {
            throw new DukeException("OOPS!!! You didn't give a valid index :(");
        }
        return command;
    }

    private static Command parseDeleteCommand(String input) throws DukeException {
        assert !input.isBlank();
        String[] args = input.trim().split("\\s+");
        assert args.length >= 1;
        assert args[0].equals("delete");
        Command command;
        try {
            command = new DeleteCommand(Integer.parseInt(args[1]) - 1);
        } catch (IndexOutOfBoundsException exception) {
            throw new DukeException("OOPS!!! No task index is specified :(");
        } catch (NumberFormatException exception) {
            throw new DukeException("OOPS!!! You didn't give a valid index :(");
        }
        return command;
    }

    private static Command parseToDoCommand(String input) throws DukeException {
        assert !input.isBlank();
        String[] args = input.trim().split("\\s+");
        assert args.length >= 1;
        assert args[0].equals("todo");
        return new AddCommand(new ToDo(Parser.parseArgument(args, "todo")
                .reduce("", (x, y) -> x + " " + y).trim(), false));
    }

    private static Command parseEventCommand(String input) throws DukeException {
        assert !input.isBlank();
        String[] args = input.trim().split("\\s+");
        assert args.length >= 1;
        assert args[0].equals("event");
        Command command;
        try {
            String description = Parser.parseArgument(args, "event")
                    .reduce("", (x, y) -> x + " " + y).trim();
            String[] timeArgument = Parser.parseArgument(args, "/at")
                    .toArray(String[]::new);
            if (timeArgument.length == 0) {
                throw new DukeException("OOPS!!! There is no /at argument for event :(");
            }
            command = new AddCommand(
                    new Event(description, false, LocalDate.parse(timeArgument[0])));
        } catch (DateTimeParseException exception) {
            throw new DukeException(
                    "OOPS!!! Can't recognize the date :(. Please input the date in yyyy-mm-dd format.");
        }
        return command;
    }

    private static Command parseDeadlineCommand(String input) throws DukeException {
        assert !input.isBlank();
        String[] args = input.trim().split("\\s+");
        assert args.length >= 1;
        assert args[0].equals("deadline");
        Command command;
        try {
            String description = Parser.parseArgument(args, "deadline")
                    .reduce("", (x, y) -> x + " " + y).trim();
            String[] timeArgument = Parser.parseArgument(args, "/by")
                    .toArray(String[]::new);
            if (timeArgument.length == 0) {
                throw new DukeException("OOPS!!! There is no /by argument for deadline :(");
            }
            command = new AddCommand(
                    new Deadline(description, false, LocalDate.parse(timeArgument[0])));
        } catch (DateTimeParseException exception) {
            throw new DukeException(
                    "OOPS!!! Can't recognize the date :(. Please input the date in yyyy-mm-dd format.");
        }
        return command;
    }

    /**
     * Returns a <code>Command</code> equivalent to the user input.
     *
     * @param input the user input
     * @return a <code>Command</code> equivalent to the user input
     * @throws DukeException if the input format is invalid
     */
    public static Command parse(String input) throws DukeException {
        assert !input.isBlank();
        String[] args = input.trim().split("\\s+");
        assert args.length >= 1;
        Command command;
        switch (args[0]) {
        case "bye":
            command = parseExitCommand(input);
            break;
        case "list":
            command = parseListCommand(input);
            break;
        case "undo":
            command = parseUndoCommand(input);
            break;
        case "find":
            command = parseFindCommand(input);
            break;
        case "mark":
            command = parseMarkCommand(input);
            break;
        case "unmark":
            command = parseUnMarkCommand(input);
            break;
        case "delete":
            command = parseDeleteCommand(input);
            break;
        case "todo":
            command = parseToDoCommand(input);
            break;
        case "event":
            command = parseEventCommand(input);
            break;
        case "deadline":
            command = parseDeadlineCommand(input);
            break;
        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :(");
        }
        return command;
    }
}
