package kirby.commands;

import java.io.IOException;

import kirby.Storage;
import kirby.TaskList;
import kirby.exceptions.KirbyMissingArgumentException;
import kirby.exceptions.KirbyOutOfRangeException;
import kirby.ui.Ui;

/**
 * DeleteCommand class handles the command to delete a task.
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    /**
     * Constructor for the class DeleteCommand.
     *
     * @param argument Argument of a command.
     */
    public DeleteCommand(String argument, TaskList tasks) throws KirbyMissingArgumentException,
            KirbyOutOfRangeException {
        if (argument == null) {
            throw new KirbyMissingArgumentException("delete");
        }
        this.taskIndex = Integer.parseInt(argument);

        int currentTaskCount = tasks.getTaskCount();
        if (taskIndex < 1 || taskIndex > currentTaskCount) {
            throw new KirbyOutOfRangeException("delete");
        }
    }

    /**
     * {@inheritDoc}
     * Deletes the specified task if arguments are valid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {

        // Should only need to remove once
        String strResult = tasks.removeTaskString(this.taskIndex - 1);
        tasks.removeTask(this.taskIndex - 1);

        try {
            storage.writeTask(tasks.getList());
        } catch (IOException e) {
            return (e.getMessage());
        }
        return strResult;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
