package zeus.command;

import java.util.ArrayList;

import zeus.main.Storage;
import zeus.main.TaskList;
import zeus.main.Ui;
import zeus.task.Task;

/**
 * Represents a Command to search for tasks.
 */
public class FindCommand extends Command {

    private String stringToMatch;

    /**
     * Constructor for FindCommand class.
     *
     * @param stringToMatch The input string to search for.
     */
    public FindCommand(String stringToMatch) {
        this.stringToMatch = stringToMatch;
    }

    /**
     * Executes the to find the matching tasks.
     *
     * @param taskList List of tasks.
     * @param ui The Ui.
     * @param storage The Storage.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> tasks = taskList.findMatchingTasks(this.stringToMatch);
        ui.printMatchingTasks(tasks);
    }
}
