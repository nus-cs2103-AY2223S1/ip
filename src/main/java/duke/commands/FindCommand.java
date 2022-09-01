package duke.commands;

import duke.storage.Storage;
import duke.storage.TaskList;

/**
 * Represents a command that finds tasks
 * whose description contains the given keyword.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_SUCCESS = "Here are the matching tasks in your list:\n";

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
     * @param storage Storage that writes to the local disk.
     * @return A string showing a message.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        try {
            TaskList newTaskList = tasks.findMatchingTasks(keyword);
            String successMessage = MESSAGE_SUCCESS + newTaskList.iterate();
            return successMessage;
        } catch (NullPointerException npe) {
            return "☹ Please enter a keyword!";
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
