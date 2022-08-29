package ted.command;

import ted.Storage;
import ted.exception.TedException;
import ted.task.Event;
import ted.task.TaskList;
import ted.ui.UiController;

/**
 * A class that encapsulate a DeadlineCommand, to
 * create a event task
 */
public class EventCommand extends Command {

    /**
     * Event's datetime
     */
    private String at;

    /**
     * Event's description
     */
    private String description;

    /**
     * Construct an event command
     * @param args
     * @throws TedException
     */
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

    /**
     * Add an event task into task list and save the current
     * task list to storage
     * @param tasks
     * @param ui
     * @param storage
     * @throws TedException
     */
    @Override
    public void run(TaskList tasks, UiController ui, Storage storage) throws TedException {
        tasks.add(new Event(description, at));
        ui.showAddedTaskSuccess(tasks);

        try {
            storage.saveTasks(tasks);
        } catch (Exception e) {
            ui.showTaskSavingError(e);
        }
    }
}
