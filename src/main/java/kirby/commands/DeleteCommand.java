package kirby.commands;

import kirby.Storage;
import kirby.TaskList;
import kirby.ui.Ui;
import kirby.exceptions.KirbyMissingArgumentException;

/**
 * DeleteCommand class handles the command to delete a task.
 */
public class DeleteCommand extends Command {
    private String inputString;

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
        if (inputString.split(" ").length != 2) {
            throw new KirbyMissingArgumentException("delete");
        }
        int taskIndex = Integer.parseInt(inputString.split(" ")[1]);
        int currentTaskCount = tasks.getTaskCount();
        if (taskIndex < 1 || taskIndex > currentTaskCount) {
            throw new KirbyMissingArgumentException("delete");
        }
        String strResult = tasks.removeTaskString(taskIndex - 1);
        tasks.removeTask(taskIndex - 1);
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
