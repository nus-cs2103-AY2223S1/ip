package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/** A class that represents a command to view the current tasks in the to-do list */
public class ViewCommand extends Command {

    private String keyword;

     public ViewCommand(String keyword) {
         this.keyword = keyword;
     }

    /**
     * Executes the listing of all current tasks in the to-do list, after receiving the appropriate input from the user.
     *
     * @param tasks The TaskList object that is keeping track of all the current tasks.
     * @param ui The UI object that displays messages to the user.
     * @param storage The storage used to save the task to file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
         ui.listTasks(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
