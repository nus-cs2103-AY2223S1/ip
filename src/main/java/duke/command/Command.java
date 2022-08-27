package duke.command;

import java.util.Locale;

import duke.exception.DukeException;
import duke.util.Storage;

/**
 * RecognisedCommand that Duke can execute
 * @author Nephelite
 * @version 0.2
 */
public abstract class Command {
    /**
     * All commands recognised by Duke
     */
    public enum RecognisedCommand {
        BYE, LIST, HELP, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, FIND, INVALID;
    }

    /**
     * Executes the command
     * @param storage Duke's storage system for tasks
     * @return Duke's response to the execution of the command
     * @throws DukeException for invalid inputs
     * @since 0.2
     */
    public abstract String execute(Storage storage) throws DukeException;

    /**
     * Checks if the command leads to the end of the current Duke session
     * @return true if the command ends the current session. Otherwise, false.
     * @since 0.1
     */
    public abstract boolean isExit();

    /**
     * Recognises the correct command from the input command
     * @param command the input command
     * @return the RecognisedCommand corresponding to the input command
     * @since 0.1
     */
    public static RecognisedCommand checkEnums(String command) {
        for (RecognisedCommand e : RecognisedCommand.values()) {
            if (e.name().equals(command.toUpperCase(Locale.ROOT))) {
                return e;
            }
        }
        return RecognisedCommand.INVALID;
    }
}
