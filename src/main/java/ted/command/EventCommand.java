package ted.command;

import ted.*;
import ted.exception.TedException;
import ted.task.Event;
import ted.task.TaskList;

public class EventCommand extends Command {

    private String at;
    private String description;

    public EventCommand(String args) throws TedException {
        super(args);
        if (args.isEmpty()) {
            throw new TedException("The description of event must not be empty.");
        }

        String[] inputs = args.split(" /at ");
        this.description = inputs[0];

        if (inputs.length <= 1) {
            throw new TedException("The datetime (use /at) of event must be set. "
                    + "If you wish to create a task without date or time, try using todo command.");
        }

        this.at = inputs[1];
    }

    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) throws TedException {
        tasks.add(new Event(description, at));
        ui.showAddedTaskSuccess(tasks);

        try {
            storage.saveTasks(tasks);
        } catch (Exception e) {
            ui.showTaskSavingError(e);
        }
    }
}
