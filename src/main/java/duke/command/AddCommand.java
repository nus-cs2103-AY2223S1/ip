package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * A command that adds tasks to the task list.
 */
public abstract class AddCommand extends Command {
    protected Task task;

    /**
     * Constructor that takes in the command string and the task to be added.
     *
     * @param command input string from user
     * @param task task to be added
     */
    public AddCommand(String command, Task task) {
        super(command);
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) throws IOException {
        taskList.add(this.task, super.command, storage);
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        try {
            this.execute(taskList, storage);
        } catch (IOException e) {
            ui.println(e.getMessage());
            return;
        }
        ui.printWithDivider(String.format("Got it. I've added this task:\n  %s", this.task.toString()));
    }
}
