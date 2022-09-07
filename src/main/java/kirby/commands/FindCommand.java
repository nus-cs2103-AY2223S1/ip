package kirby.commands;

import java.util.ArrayList;

import kirby.Storage;
import kirby.TaskList;
import kirby.exceptions.KirbyMissingArgumentException;
import kirby.tasks.Task;
import kirby.ui.Ui;

/**
 * FindCommand class handles the command to find the list of tasks that contains a specified keyword.
 */
public class FindCommand extends Command {
    private static final int FIND_COMMAND_LENGTH = 2;
    private final String inputString;

    /**
     * Constructor for the class FindCommand.
     *
     * @param inputString Arguments of a command.
     */
    public FindCommand(String inputString) {
        this.inputString = inputString;
    }

    /**
     * {@inheritDoc}
     * Lists down the list of tasks that contains the keyword.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws KirbyMissingArgumentException {
        if (inputString.split(" ").length != FIND_COMMAND_LENGTH) {
            throw new KirbyMissingArgumentException("find");
        }
        String keyword = inputString.split(" ")[1];
        ArrayList<Task> tasksFound = tasks.findTask(keyword);
        if (tasksFound.size() < 1) {
            return "No tasks found!";
        }
        StringBuilder taskListPara = new StringBuilder();
        for (Task task: tasksFound) {
            taskListPara.append(task.toString()).append("\n");
        }
        return taskListPara.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
