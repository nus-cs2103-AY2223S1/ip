package ted.command;

import ted.Storage;
import ted.exception.TedException;
import ted.task.TaskList;
import ted.ui.UiController;

public class FindCommand extends Command {

    /**
     * Search string provided by user
     */
    private String searchString;

    /**
     * Construct a FindCommand instance
     * @param args
     */
    public FindCommand(String args) {
        super(args);
        this.searchString = args;
    }

    /**
     * Iterate through tasks and find tasks that match
     * user's search string
     * @param tasks
     * @param ui
     * @param storage
     * @throws TedException
     */
    @Override
    public void run(TaskList tasks, UiController ui, Storage storage) throws TedException {
        TaskList matchedTasks = TaskList.empty();

        for (int index = 0;index < tasks.size();index++) {
            if (tasks.get(index).contains(this.searchString)) {
                matchedTasks.add(tasks.get(index));
            }
        }

        ui.output("I found the following tasks: \n" + matchedTasks.toString());
    }
}
