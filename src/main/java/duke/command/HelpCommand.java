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
    private final String[] commandNames = {
        "todo <description>",
        "deadline <description> /by <time>",
        "event <description> /at <time>",
        "list",
        "mark <num>",
        "unmark <num>",
        "delete <num>",
        "find <text>",
        "clear",
        "help",
        "bye"
    };
    private final String[] commandDescriptions = {
        "Creates task with given description.",
        "Creates deadline with given description set by given time.\n"
                + "          Time must be formatted as: yyyy-mm-dd hhmm",
        "Creates event with the given description set at given time.\n"
                + "          Time must be formatted as: yyyy-mm-dd hhmm",
        "Lists all currently saved tasks.",
        "Marks given task num from \"list\" as complete.",
        "Marks given task num from \"list\" as incomplete.",
        "Deletes given task num from \"list\" permanently.",
        "Returns all tasks whose descriptions match given text.",
        "Clears window of all dialogues.",
        "Shows list of commands",
        "Saves all tasks to storage and exits Artemis.",
    };
    private final String[] commandUsage = {
        "todo Write essay",
        "deadline Finish essay /by 2022-12-12 1800",
        "event Submit essay /at 2022-12-12 2359",
        "list",
        "mark 1",
        "umark 1",
        "delete 1",
        "find essay",
        "clear",
        "help",
        "bye"
    };
    /**
     * {@inheritDoc}
     * Shows list of available commands to user.
     */
    @Override
    public String execute(TaskList itemList, Storage storage) throws DukeException {
        StringBuilder availableCommands = new StringBuilder(
                "Here is a list of case-sensitive available commands:\n");
        for (int i = 0; i < commandNames.length; i++) {
            availableCommands
                    .append(commandNames[i])
                    .append("\n          ")
                    .append(commandDescriptions[i])
                    .append("\n          eg. ")
                    .append(commandUsage[i])
                    .append(i + 1 == commandNames.length ? "" : "\n");
        }
        return availableCommands.toString();
    }
}
