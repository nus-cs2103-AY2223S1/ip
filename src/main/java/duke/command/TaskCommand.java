package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public abstract class TaskCommand extends Command {

    private final Task newTask;

    public TaskCommand(Task newTask) {
        this.newTask = newTask;
    }

    @Override
    public void exec(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(newTask);
        ui.showReply(String.format("Gotcha! I added the following task to the list:\n"
                        + "  %s\n"
                        + "Currently, I have %d tasks recorded",
                newTask, tasks.getLength()));
        storage.save(tasks);
    }

    @Override
    public boolean isTerminator() {
        return false;
    }
}
