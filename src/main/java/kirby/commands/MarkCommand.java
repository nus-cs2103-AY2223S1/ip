package kirby.commands;

import java.io.IOException;

import kirby.Storage;
import kirby.TaskList;
import kirby.exceptions.KirbyMissingArgumentException;
import kirby.ui.Ui;

/**
 * MarkCommand class handles the command to mark a task.
 */
public class MarkCommand extends Command {
    private static final int MARK_COMMAND_LENGTH = 2;
    private final String inputString;

    /**
     * Constructor for the class DeadlineCommand.
     *
     * @param inputString arguments of a command.
     */
    public MarkCommand(String inputString) {
        this.inputString = inputString;
    }

    /**
     * {@inheritDoc}
     * Marks the specified task if arguments are valid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws KirbyMissingArgumentException {
        if (inputString.split(" ").length != MARK_COMMAND_LENGTH) {
            throw new KirbyMissingArgumentException("mark");
        }
        int taskIndex = Integer.parseInt(inputString.split(" ")[1]);
        int currentTaskCount = tasks.getTaskCount();
        if (taskIndex < 1 || taskIndex > currentTaskCount) {
            throw new KirbyMissingArgumentException("mark");
        }
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
