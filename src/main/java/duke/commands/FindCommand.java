package duke.commands;

import duke.storage.Storage;
import duke.storage.TaskList;
import duke.ui.Ui;

/**
 * Represents a command that finds tasks
 * whose description contains the given keyword.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_SUCCESS = "Here are the matching tasks in your list:";

    private String keyword;

    /**
     * Constructs a new FindCommand instance with the given keyword.
     *
     * @param keyword Keyword to be checked.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command by listing all tasks
     * whose description contains the given keyword.
     *
     * @param tasks Task list that stores existing tasks.
     * @param ui Ui that shows message to the user.
     * @param storage Storage that writes to the local disk.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            TaskList newTaskList = tasks.findMatchingTasks(keyword);
            ui.showSuccessMessage(MESSAGE_SUCCESS);
            newTaskList.iterate();
        } catch (NullPointerException npe) {
            ui.showError("☹ Please enter a keyword!");
        }
    }

    /**
     * Keeps the programme running.
     *
     * @return True.
     */
    @Override
    public boolean isRunning() {
        return true;
    }
}
