package duke.command;

import duke.common.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to search keyword
 *
 * @author Pontakorn Prasertsuk
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Constructs a new FindCommand instance.
     *
     * @param keyword the keyword to search
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the FindCommand
     *
     * @param taskList the task list to be searched
     * @param ui the user interface to be used
     * @param storage not being used
     * @return output to be shown
     * @throws DukeException if an error occurs
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        TaskList filtered = taskList.filter(keyword);
        if (filtered.getTaskList().isEmpty()) {
            ui.showOutput("No matching task found!");
            return "No matching task found!";
        } else {
            ui.showOutput("Here are the matching tasks in your list:");
            ui.showOutput(filtered.toString());

            return "Here are the matching tasks in your list:\n" + filtered.toString();
        }
    }

    /**
     * Returns false as this is not the exit command
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
