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
    private String inputString;

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
        if (inputString.split(" ").length != 2) {
            throw new KirbyMissingArgumentException("find");
        }
        String keyword = inputString.split(" ")[1];
        ArrayList<Task> res = tasks.findTask(keyword);
        String taskListPara = "";
        for (Task task: res) {
            taskListPara += res + "\n";
        }
        return taskListPara;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
