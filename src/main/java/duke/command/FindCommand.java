package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to find a task by searching a keyword.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    private String keyword;

    /**
     * Constructor for the FindCommand.
     * @param keyword The keyword used to find list of tasks that matches it.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Find tasks in the task list that matches with the keyword the user
     * input.
     * @param task The TaskList object of the chatbot.
     * @param ui The Ui object of the chatbot.
     * @param storage The storage object of the chatbot.
     */
    @Override
    public void execute(TaskList task, Ui ui, Storage storage) {
        ui.displayMatchedTasks(task.FindTasks(this.keyword));
    }

    /**
     * Return true if the command is exit command.
     * @return Return true if the command is an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
