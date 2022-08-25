package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Finds Tasks in a TaskList provided a given keyword.
 */
public class FindCommand extends Command {
    /** The keyword to run FindCommand */
    public static final String COMMAND_NAME = "find";

    private final String keyword;

    /**
     * Constructs a FindCommand instance with the provided keyword.
     *
     * @param keyword keyword to be searched.
     * @throws DukeException if the keyword is an empty String or null.
     */
    public FindCommand(String keyword) throws DukeException {
        if (keyword == null || keyword.equals("")) {
            throw new DukeException("Please give me a keyword so I can find the tasks you want!");
        }
        this.keyword = keyword;
    }

    /**
     * Searches through the provided TaskList with its keyword and passes the results
     * to UI.
     *
     * @param tasks the TaskList to be searched through.
     * @param ui the Ui where results are passed to.
     * @param storage unused for FindCommand.
     */
    public void exec(TaskList tasks, Ui ui, Storage storage) {
        TaskList filteredTasks = tasks.filter((task) -> task.hasSubstring(this.keyword));
        StringBuilder reply = new StringBuilder();
        String[] stringifiedTaskList = filteredTasks.toStringList();
        reply.append("Here are the matching tasks I have found:");
        if (stringifiedTaskList.length == 0) {
            reply.append("\nNo tasks of the sort found.");
        }
        for (int i = 0; i < stringifiedTaskList.length; i++) {
            reply.append(String.format("\n%02d. %s", i + 1, stringifiedTaskList[i]));
        }
        ui.showReply(reply.toString());
    }

    /**
     * Returns false as Find is not a terminating Command.
     *
     * @return false.
     */
    public boolean isTerminator() {
        return false;
    }
}
