package duke.command;

import duke.storage.Storage;

import duke.task.Task;
import duke.task.TaskList;

import duke.ui.Ui;

import java.util.ArrayList;

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

    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> result = tasks.filterTasks(this.keyWord);
        ui.printFilteredTasks(result);
    }
}
