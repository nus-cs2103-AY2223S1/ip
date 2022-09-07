package kirby.commands;

import java.io.IOException;

import kirby.Storage;
import kirby.TaskList;
import kirby.exceptions.KirbyMissingArgumentException;
import kirby.ui.Ui;

/**
 * UnmarkCommand class handles the command to unmark a task.
 */
public class UnmarkCommand extends Command {
    private static final int UNMARK_COMMAND_LENGTH = 2;
    private final String inputString;

    /**
     * Constructor for the class DeadlineCommand.
     *
     * @param inputString Arguments of a command.
     */
    public UnmarkCommand(String inputString) {
        this.inputString = inputString;
    }

    /**
     * {@inheritDoc}
     * Marks the specified task as incomplete if arguments are valid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws KirbyMissingArgumentException {
        if (inputString.split(" ").length != UNMARK_COMMAND_LENGTH) {
            throw new KirbyMissingArgumentException("unmark");
        }
        int taskIndex = Integer.parseInt(inputString.split(" ")[1]);
        int currentTaskCount = tasks.getTaskCount();
        if (taskIndex < 1 || taskIndex > currentTaskCount) {
            throw new KirbyMissingArgumentException("unmark");
        }
        tasks.setTaskUnmarked(taskIndex - 1);

        try {
            storage.writeTask(tasks.getList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks.setUnmarkedString(taskIndex - 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
