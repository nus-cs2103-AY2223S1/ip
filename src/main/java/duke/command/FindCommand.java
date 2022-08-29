package duke.command;

import duke.exception.DukeException;
import duke.exception.DukeNoMatchFoundException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * FindCommand is a Command that handles finding a task with a specific keyword.
 *
 * @author John Russell Himawan
 * @version CS2103T AY22/23 Sem 1
 */
public class FindCommand extends Command {
    private String keyword;
    /**
     * Constructor for FindCommand.
     *
     * @param
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Outputs a list of the tasks matching the specific keyword.
     *
     * @param tasks A TaskList containing the Tasks.
     * @param ui The Ui which handles interactions with the user.
     * @param storage The Storage which handles loading and saving data from the file.
     * @throws DukeException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        TaskList matchedTasks = new TaskList();
        int size = tasks.getSize();

        for (int i = 0; i < size; i++) {
            Task task = tasks.getTask(i);
            if (task.getDescription().contains(keyword)) {
                matchedTasks.addTask(task);
            }
        }

        if (matchedTasks.getSize() < 1) {
            throw new DukeNoMatchFoundException(keyword);
        }

        ui.displayMatch(matchedTasks);
    }
}
