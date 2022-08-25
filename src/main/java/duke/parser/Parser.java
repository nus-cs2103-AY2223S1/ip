package duke.parser;

import duke.DukeException;
import duke.command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.util.stream.Stream;

public class Parser {

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

            default: {
                return new UnknownCommand();
            }
        }
    }

    /* Parses a command into an array of Arguments, where n is the number of
       parameters passed by the user. Parameters are seperated by a "/".
       Each parameter consists of two values, its name, which is the first
       word, and its body which contains the rest of the parameter.
     */
    private static Argument[] processArgs(String command) {
        return Stream.of(command.trim().split("\\s+/"))
                .map(s -> s.split("\\s+", 2))
                .map(arr -> {
                    if (arr.length == 1) return new Argument(arr[0]);
                    else return new Argument(arr[0], arr[1]);
                })
                .toArray(Argument[]::new);
    }

    private static int parseIndex(String idx) throws DukeException {
        try {
            int indexValue = Integer.parseInt(idx) - 1;
            if (indexValue < 0) {
                throw new DukeException("You must pass a positive integer value. " + idx + " is a non-positive integer value.");
            }
            return indexValue;
        } catch (NumberFormatException e) {
            if (idx == null) {
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

}
