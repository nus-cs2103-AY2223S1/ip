package ted.command;

import ted.Storage;
import ted.task.TaskList;
import ted.exception.TedException;
import ted.Ui;

public class ListCommand extends Command {

    /**
     * Construct a list command
     * @param args
     */
    public ListCommand(String args) {
        super(args);
    }

    /**
     * List out all the tasks
     * @param tasks
     * @param ui
     * @param storage
     * @throws TedException
     */
    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) throws TedException {
        if (tasks.size() == 0) {
            ui.outputLine("There is no tasks here. Feel free to add some tasks.");
            return;
        }

        ui.output(tasks.toString());
    }
}
