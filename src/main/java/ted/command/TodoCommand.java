package ted.command;

import ted.Storage;
import ted.Ui;
import ted.exception.TedException;
import ted.task.TaskList;
import ted.task.ToDo;

/**
 * A class that encapsulate a DeadlineCommand, to
 * create a todo task
 */
public class TodoCommand extends Command {

    /**
     * Description of todo
     */
    private String description;

    /**
     * Construct a todo command
     * @param args
     * @throws TedException
     */
    public TodoCommand(String args) throws TedException {
        super(args);
        if (args.isEmpty()) {
            throw new TedException("The description of todo must not be empty.");
        }

        this.description = args;
    }

    /**
     * Add the todo into tasks and save the current tasks list
     * to storage.
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(new ToDo(description));
        ui.showAddedTaskSuccess(tasks);

        try {
            storage.saveTasks(tasks);
        } catch (Exception e) {
            ui.showTaskSavingError(e);
        }
    }
}
