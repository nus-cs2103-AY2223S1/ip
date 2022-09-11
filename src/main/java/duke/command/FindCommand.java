package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Find Command class to find class based on keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructor for find command class.
     *
     * @param keyword Keyword to find.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Find tasks in the tasks based on keyword.
     *
     * @param tasks The task to be executed.
     */
    @Override
    public String execute(TaskList tasks) {
        int i = 0;
        String response = "";
        for (Task task : tasks.getTasks()) {
            if (task.getDescription().toLowerCase().contains(this.keyword.toLowerCase())) {
                i = i + 1;
                response = response + i + "." + task + "\n";
            }
        }
        return Ui.showFindTaskMessage(this.keyword) + response;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
