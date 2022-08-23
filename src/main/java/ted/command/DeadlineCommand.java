package ted.command;

import ted.*;
import ted.exception.TedException;
import ted.task.Deadline;
import ted.task.TaskList;

public class DeadlineCommand extends Command {

    private String by;
    private String description;

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

    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) throws TedException {
        tasks.add(new Deadline(description, by));
        ui.showAddedTaskSuccess(tasks);

        try {
            storage.saveTasks(tasks);
        } catch (Exception e) {
            ui.showTaskSavingError(e);
        }
    }
}
