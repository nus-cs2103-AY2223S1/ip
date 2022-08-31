package duke.command;

import duke.task.TaskList;

/**
 * Find Command class to find class based on keyword.
 */
public class FindCommand extends Command {
    private String Keyword;

    /**
     * Constructor for find command class.
     *
     * @param keyword Keyword to find.
     */
    public FindCommand(String keyword) {
        this.Keyword = keyword;
    }

    /**
     * Find tasks in the tasks based on keyword.
     *
     * @param tasks The task to be executed.
     */
    @Override
    public void execute(TaskList tasks) {
        tasks.findTask(this.Keyword);
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
