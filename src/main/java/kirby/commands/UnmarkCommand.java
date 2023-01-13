package kirby.commands;

import java.io.IOException;

import kirby.Storage;
import kirby.TaskList;
import kirby.exceptions.KirbyMissingArgumentException;
import kirby.exceptions.KirbyOutOfRangeException;
import kirby.ui.Ui;

/**
 * UnmarkCommand class handles the command to unmark a task.
 */
public class UnmarkCommand extends Command {
    private final int taskIndex;

    /**
     * Constructor for the class UnmarkCommand.
     *
     * @param argument Argument of a command.
     */
    public UnmarkCommand(String argument, TaskList tasks) throws KirbyMissingArgumentException,
            KirbyOutOfRangeException {
        if (argument == null) {
            throw new KirbyMissingArgumentException("mark");
        }
        this.taskIndex = Integer.parseInt(argument);

        int currentTaskCount = tasks.getTaskCount();
        if (taskIndex < 1 || taskIndex > currentTaskCount) {
            throw new KirbyOutOfRangeException("unmark");
        }
    }

    /**
     * {@inheritDoc}
     * Unmarks the specified task if arguments are valid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
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
