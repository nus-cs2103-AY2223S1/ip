package ted.command;

import ted.Storage;
import ted.exception.TedException;
import ted.task.Deadline;
import ted.task.TaskList;
import ted.ui.UiController;

/**
 * A class that encapsulate a DeadlineCommand, to
 * create a deadline task
 */
public class DeadlineCommand extends Command {

    /**
     * Deadline of the task
     */
    private String by;

    /**
     * Description of the task
     */
    private String description;

    /**
     * Construct a deadline command.
     * @param args
     * @throws TedException
     */
    public DeadlineCommand(String args) throws TedException {
        super(args);
        if (args.isEmpty()) {
            throw new TedException("The description of deadline must not be empty.");
        }

        String[] inputs = args.split(" /by ");
        this.description = inputs[0];

        if (inputs.length < 2) {
            throw new TedException("The deadline (use /by yyyy-mm-dd) of task must be set. "
                    + "If you wish to create a task without deadline, try using todo command.");
        }

        this.by = inputs[1];
    }

    /**
     * Add a deadline task into task list and save task list
     * to storage.
     * @param tasks
     * @param ui
     * @param storage
     * @throws TedException
     */
    @Override
    public void run(TaskList tasks, UiController ui, Storage storage) throws TedException {
        tasks.add(new Deadline(description, by));
        ui.showAddedTaskSuccess(tasks);

        try {
            storage.saveTasks(tasks);
        } catch (Exception e) {
            ui.showTaskSavingError(e);
        }
    }
}
