package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;

/**
 * Encapsulates the sending of a list of available commands.
 *
 * @author Kartikeya
 */
public class HelpCommand implements Command {
    private static final String[] COMMAND_NAMES = {
        "todo <description>",
        "deadline <description> /by <time>",
        "event <description> /at <time>",
        "list",
        "mark <num>",
        "unmark <num>",
        "description <num> <new description>",
        "time <num> <new time>",
        "delete <num>",
        "find <text>",
        "clear",
        "help",
        "bye"
    };
    private static final String[] COMMAND_DESCRIPTIONS = {
        "Creates task with given description.",
        "Creates deadline with given description set by given time.\n"
                + "      24-hr time must be formatted as: \"yyyy-mm-dd hhmm\".",
        "Creates event with the given description set at given time.\n"
                + "      24-hr time must be formatted as: \"yyyy-mm-dd hhmm\".",
        "Lists all currently saved tasks.",
        "Marks given task num from \"list\" as complete.",
        "Marks given task num from \"list\" as incomplete.",
        "Updates description of task num from \"list\".",
        "Updates time of task num from \"list\".\n"
                + "      24-hr time must be formatted as: \"yyyy-mm-dd hhmm\".",
        "Deletes given task num from \"list\" permanently.",
        "Returns all tasks whose descriptions match given text.",
        "Clears window of all messages.",
        "Shows all available commands.",
        "Saves all tasks to storage and exits Artemis.",
    };
    private static final String[] COMMAND_USAGES = {
        "todo Write essay",
        "deadline Finish essay /by 2022-12-12 1800",
        "event Submit essay /at 2022-12-12 2359",
        "list",
        "mark 1",
        "unmark 1",
        "description 1 Submit letter",
        "time 1 2022-12-11 1700",
        "delete 1",
        "find essay",
        "clear",
        "help",
        "bye"
    };
    /**
     * {@inheritDoc}
     * Shows confirmation list of available commands to user.
     */
    @Override
    public String execute(TaskList itemList, Storage storage) throws DukeException {
        return "Displaying list of available commands...";
    }

    /**
     * Returns string representing list of available commands for user.
     *
     * @return the string representing a list of available commands
     */
    public static String getHelpList() {
        StringBuilder availableCommands = new StringBuilder(
                "Here is a list of (case-sensitive) available commands:\n");
        for (int i = 0; i < COMMAND_NAMES.length; i++) {
            availableCommands
                    .append(COMMAND_NAMES[i])
                    .append("\n      ")
                    .append(COMMAND_DESCRIPTIONS[i])
                    .append("\n      eg. ")
                    .append(COMMAND_USAGES[i])
                    .append(i + 1 == COMMAND_NAMES.length ? "" : "\n");
        }
        return availableCommands.toString();
    }
}
