package duke.command;

import duke.DukeException;

import java.util.Locale;

/**
 * RecognisedCommand that Duke can execute
 * @author Nephelite
 * @version 0.1
 */
public abstract class Command {
    /**
     * All commands recognised by Duke
     */
    public enum RecognisedCommand {
        BYE, LIST, HELP, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, INVALID;
    }

    /**
     * Executes the command
     * @throws DukeException
     * @since 0.1
     */
    public abstract void execute() throws DukeException;

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
