package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.task.Task;

/**
 * Command for adding of tasks.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructor for AddCommand.
     *
     * @param task Task to add to TaskList when command is executed.
     */
    public AddCommand(Task task) {
        super.isExit = false;
        this.task = task;
    }

    /**
     * {@inheritDoc}
     * Adds task to TaskList, Storage and displays update message.
     */
    @Override
    public String execute() throws DukeException {
        Command.taskList.add(this.task);
        Command.storage.save(this.task);
        return Ui.getAddTaskMessage(this.task, Command.taskList.size());
    }
}
