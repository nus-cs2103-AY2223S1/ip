package kirby.commands;

import java.io.IOException;

import kirby.Storage;
import kirby.TaskList;
import kirby.exceptions.KirbyMissingArgumentException;
import kirby.tasks.Deadline;
import kirby.ui.Ui;

/**
 * DeadlineCommand class handles the command to create a Deadline task.
 */
public class DeadlineCommand extends Command {
    private final Deadline deadline;

    /**
     * Constructor for the class DeadlineCommand.
     *
     * @param arguments Arguments of a command.
     */
    public DeadlineCommand(String[] arguments) throws KirbyMissingArgumentException {
        String taskName;
        String time;
        taskName = arguments[0];
        time = arguments[1];
        if (taskName == null || time == null) {
            throw new KirbyMissingArgumentException("deadline");
        }
        this.deadline = new Deadline(taskName, time);
    }

    /**
     * {@inheritDoc}
     * Creates a Deadline task if arguments are valid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(this.deadline);
        try {
            storage.writeTask(tasks.getList());
        } catch (IOException e) {
            return (e.getMessage());
        }
        return tasks.addTaskString(deadline);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
