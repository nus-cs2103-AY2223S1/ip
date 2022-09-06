package duke.parser;

import java.util.stream.Stream;

import duke.DukeException;
import duke.command.ArchiveCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.TodoCommand;
import duke.command.UnknownCommand;
import duke.command.UnmarkCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * This class handles parsing of user commands to Command objects.
 */
public class Parser {

    /**
     * Parses the specified String parameter into a Command object.
     *
     * @param command the String to be parsed.
     * @return Command.
     * @throws DukeException if command is malformed.
     */
    public static Command parse(String command) throws DukeException {

        Argument[] args = processArgs(command);
        String commandName = args[0].getName();

        switch (commandName) {
        case ListCommand.COMMAND_NAME: {
            return new ListCommand();
        }

        case ByeCommand.COMMAND_NAME: {
            return new ByeCommand();
        }

        case TodoCommand.COMMAND_NAME: {
            return new TodoCommand(new Todo(args[0].getBody()));
        }

        case DeadlineCommand.COMMAND_NAME: {
            return parseDeadlineArgs(args);
        }

        case EventCommand.COMMAND_NAME: {
            return parseEventArgs(args);
        }

        case MarkCommand.COMMAND_NAME: {
            return new MarkCommand(parseIndex(args[0].getBody()));
        }

        case UnmarkCommand.COMMAND_NAME: {
            return new UnmarkCommand(parseIndex(args[0].getBody()));
        }

        case DeleteCommand.COMMAND_NAME: {
            return new DeleteCommand(parseIndex(args[0].getBody()));
        }

        case FindCommand.COMMAND_NAME: {
            return new FindCommand(args[0].getBody());
        }

        case ArchiveCommand.COMMAND_NAME: {
            return parseArchiveArgs(args);
        }

        default: {
            return new UnknownCommand();
        }

        }
    }

    /**
     * Parses a command into an array of Arguments.
     * <p>
     * A command is seperated into Arguments by "{@literal <whitespace>}/".
     * <p>
     * Each Argument is then seperated into two Strings, the name and its body,
     * using whitespace.
     *
     * @param command The command to be parsed
     * @return an array of Arguments.
     */
    private static Argument[] processArgs(String command) {
        return Stream.of(command.trim().split("\\s+/"))
                .map(s -> s.split("\\s+", 2))
                .map(arr -> {
                    if (arr.length == 1) {
                        return new Argument(arr[0]);
                    } else {
                        assert arr.length == 2 : "Argument array doesn't have the expected array length of two";
                        return new Argument(arr[0], arr[1]);
                    }
                })
                .toArray(Argument[]::new);
    }

    /**
     * Parses the specified String parameter as an index value.
     *
     * @param idx the specified String parameter.
     * @return an int value.
     * @throws DukeException if idx is not a positive integer.
     */
    private static int parseIndex(String idx) throws DukeException {
        try {
            int indexValue = Integer.parseInt(idx) - 1;
            if (indexValue < 0) {
                throw new DukeException("You must pass a positive integer value. "
                        + idx + " is a non-positive integer value.");
            }
            return indexValue;
        } catch (NumberFormatException e) {
            if (idx == null || idx.equals("")) {
                throw new DukeException("You must pass an index value.", e);
            } else {
                throw new DukeException("You must pass an integer value. " + idx + " is not an integer.", e);
            }
        }
    }

    private static String getArgBody(Argument[] args, String argumentName) {
        for (int i = 1; i < args.length; i++) {
            if (args[i].getName().equals(argumentName)) {
                return args[i].getBody();
            }
        }
        return null;
    }

    private static EventCommand parseEventArgs(Argument[] args) throws DukeException {
        String atParam = getArgBody(args, "at");
        return new EventCommand(new Event(args[0].getBody(), atParam));
    }

    private static DeadlineCommand parseDeadlineArgs(Argument[] args) throws DukeException {
        String byParam = getArgBody(args, "by");
        return new DeadlineCommand(new Deadline(args[0].getBody(), byParam));
    }

    private static ArchiveCommand parseArchiveArgs(Argument[] args) {
        String forceParam = getArgBody(args, "force");
        if (args[0].getBody() != null && !args[0].getBody().equals("")) {
            return new ArchiveCommand(args[0].getBody(), forceParam != null);
        } else {
            return new ArchiveCommand(forceParam != null);
        }
    }

}
