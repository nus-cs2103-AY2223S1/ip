package duke.commands;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.List;
import duke.task.Task;
import duke.ui.Ui;

public class AddCommand extends Command {

    public static final String ADD_TODO = "todo";
    public static final String ADD_DEADLINE = "deadline";
    public static final String ADD_EVENT = "event";

    public static final String MESSAGE_SUCCESS = "Got it! I've added this task: \n"
            + "%1$s" + "\n"
            + "You have " + "%2$s" + " tasks in the list.\n";

    public Task toAdd;

    public AddCommand(Task toAdd) {
        this.toAdd = toAdd;
    }

    @Override
    public void execute(List tasks, Ui ui, Storage storage) {
        try {
            tasks.addTask(toAdd);
            storage.save();
            ui.showToUser(String.format(MESSAGE_SUCCESS, toAdd, tasks.numberOfTasks()));
        } catch (DukeException e) {
            ui.showErrorMessage(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
