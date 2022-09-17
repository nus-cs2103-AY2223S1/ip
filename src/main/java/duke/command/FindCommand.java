package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;

public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructor for <code>FindCommand</code>.
     * @param keyword
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String getResponse(TaskList tasks, Storage storage) {
        TaskList matches = tasks.findTasks(keyword);
        if (matches.getNumberOfTasks() == 0) {
            return "Baa! You don't have any tasks containing this keyword";
        }
        return matches.toString();
    }

    /**
     * Check if the command exit duke.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
