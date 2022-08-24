package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command to execute listing out all the tasks in the taskList
 * @author Nephelite
 * @version 0.1
 */
public class ListCommand extends Command {
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for a ListCommand
     * @param tasks TaskList Duke is using
     * @param ui Ui Duke is using
     * @since 0.1
     */
    public ListCommand(TaskList tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    @Override
    public void execute(Storage storage) throws DukeException {
        ui.list();
        tasks.printList();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
