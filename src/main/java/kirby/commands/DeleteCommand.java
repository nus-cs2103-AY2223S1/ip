package kirby.commands;

import java.io.IOException;

import kirby.Storage;
import kirby.TaskList;
import kirby.exceptions.KirbyMissingArgumentException;
import kirby.ui.Ui;

/**
 * DeleteCommand class handles the command to delete a task.
 */
public class DeleteCommand extends Command {
    private static final int DELETE_COMMAND_LENGTH = 2;
    private final String inputString;

    /**
     * Constructor for the class DeleteCommand.
     *
     * @param inputString Arguments of a command.
     */
    public DeleteCommand(String inputString) {
        this.inputString = inputString;
    }

    /**
     * {@inheritDoc}
     * Deletes the specified task if arguments are valid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws KirbyMissingArgumentException {
        if (inputString.split(" ").length != DELETE_COMMAND_LENGTH) {
            throw new KirbyMissingArgumentException("delete");
        }
        int taskIndex = Integer.parseInt(inputString.split(" ")[1]);
        int currentTaskCount = tasks.getTaskCount();
        if (taskIndex < 1 || taskIndex > currentTaskCount) {
            throw new KirbyMissingArgumentException("delete");
        }
        String strResult = tasks.removeTaskString(taskIndex - 1);
        tasks.removeTask(taskIndex - 1);
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
