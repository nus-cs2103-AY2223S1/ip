package duke.command;

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

    /**
     * {@inheritDoc}
     * @param storage Duke's storage system for tasks
     */
    @Override
    public void execute(Storage storage) {
        ui.list();
        tasks.printList();
    }
    /**
     * {@inheritDoc}
     * @return true if the command ends the current session. Otherwise, false.
     * @since 0.1
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
