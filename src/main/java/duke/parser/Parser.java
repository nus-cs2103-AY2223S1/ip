package duke.parser;

import java.util.HashMap;

import duke.command.*;
import duke.dukeexception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
/**
 * Represents a function of Duke robot, which can produce command corresponding to user input.
 */
public class Parser {


    /**
     * Produce different type of command for execution, corresponding to user input.
     * Throws DukeException when the user input format is not right.
     * @param fullCommand A string of a line from System.in. Can be of any format.
     * @return A certain kind of command waiting for execution.
     * @throws DukeException Throws DukeException with remind message when the input format is wrong.
     */
    public static String parse(String fullCommand, TaskList t, Storage s) throws DukeException {
        if (t == null) {
            s.clear();
            throw new DukeException("Sorry, something went wrong when loading tasks." +
                    "I have clear the file for you." +
                    "Please restart and try again.");
        }
        CommandType c = CommandType.commandMap.get(fullCommand.split(" ")[0]);
        if (c == null) {
            throw new DukeException("Sorry, I don't get what you are saying.");
        }
        switch (c) {
        case BYE:
             return new ExitCommand().execute(t, s, c);
        case EVENT:
        case TODO:
        case DEADLINE:
            return new AddCommand(fullCommand).execute(t, s, c);
        case DELETE:
            return new DeleteCommand(fullCommand).execute(t, s, c);
        case MARK:
        case UNMARK:
            return new MarkingCommand(fullCommand).execute(t, s, c);
        case GET:
        case LIST:
        case FIND:
            return new OtherCommand(fullCommand).execute(t, s, c);
        case WRONG:
            s.loadTasks();
        default:
            throw new DukeException("Sorry, I don't know your meanings.");
        }
    }
}
