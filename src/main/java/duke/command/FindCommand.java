package duke.command;

import java.util.ArrayList;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command that filters out TaskList and prints out those with matching keywords.
 */
public class FindCommand extends Command {

    private final String keyWord;

    /**
     * Constructor for FindCommand.
     * @param keyWord The keyword to filter.
     */
    public FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    /**
     * Executes the command given.
     * Command will be set to filer TaskList to those that match keyword.
     * @param tasks The list that contains all the Tasks on the program.
     * @param ui Deals with the interaction with user.
     * @param storage Deals with the loading and updating of file.
     */
    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> result = tasks.filterTasks(this.keyWord);
        ui.printFilteredTasks(result);
    }
}
