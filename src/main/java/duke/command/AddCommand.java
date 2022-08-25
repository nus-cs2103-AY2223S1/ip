package duke.command;

import duke.exception.DukeException;

import duke.storage.Storage;

import duke.task.TaskList;
import duke.task.Task;

import duke.ui.Ui;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasklist) throws DukeException {
        tasklist.loadTask(task);
        ui.formatMessage("Got it. I've added this task:\n\t " + task);
        if (tasklist.size() == 1) {
            ui.formatMessage(String.format("Now you have %d task in the list.", tasklist.size()));
        } else {
            ui.formatMessage(String.format("Now you have %d tasks in the list.", tasklist.size()));
        }
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
