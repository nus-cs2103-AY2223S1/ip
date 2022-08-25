package duke.util;

import duke.DukeException;
import duke.command.*;

/**
 * Class that deals with making sense of the user command.
 */
public class Parser {

    /**
     * Parse the user input.
     *
     * @param input the input
     * @return the command to be executed
     * @throws DukeException the duke exception
     */
    public static Command parseCommand(String input) throws DukeException {
        String[] segments = input.split(" ", 2);
        try {
            switch (segments[0]) {
                case "list":
                    return new ListCommand();

                case "mark":
                    return new MarkCommand(segments[1]);

                case "unmark":
                    return new UnmarkCommand(segments[1]);

                case "todo":
                    return new TodoCommand(segments[1]);

                case "event":
                    return new EventCommand(segments[1]);

                case "deadline":
                    return new DeadlineCommand(segments[1]);

                case "delete":
                    return new DeleteCommand(segments[1]);

                case "bye":
                    return new ByeCommand();

                default:
                    return new UnknownCommand();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Description of a " + segments[0] + " cannot be empty");
        }
    }
}
