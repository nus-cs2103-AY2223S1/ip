package kirby.commands;

import java.io.IOException;

import kirby.Storage;
import kirby.TaskList;
import kirby.exceptions.KirbyMissingArgumentException;
import kirby.exceptions.KirbyOutOfRangeException;
import kirby.ui.Ui;

/**
 * MarkCommand class handles the command to mark a task.
 */
public class MarkCommand extends Command {
    private final int taskIndex;

    /**
     * Constructor for the class MarkCommand.
     *
     * @param argument Argument of a command.
     */
    public MarkCommand(String argument, TaskList tasks) throws KirbyMissingArgumentException, KirbyOutOfRangeException {
        if (argument == null) {
            throw new KirbyMissingArgumentException("mark");
        }
        this.taskIndex = Integer.parseInt(argument);

        int currentTaskCount = tasks.getTaskCount();
        if (taskIndex < 1 || taskIndex > currentTaskCount) {
            throw new KirbyOutOfRangeException("mark");
        }
    }

    /**
     * {@inheritDoc}
     * Marks the specified task if arguments are valid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.setTaskMarked(taskIndex - 1);
        try {
            storage.writeTask(tasks.getList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks.setMarkedString(taskIndex - 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
