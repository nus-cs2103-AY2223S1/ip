package kkbot.commands;

import kkbot.storage.Storage;
import kkbot.storage.exceptions.StorageException;
import kkbot.tasks.Task;
import kkbot.tasklist.TaskList;
import kkbot.tasklist.exceptions.InvalidTaskException;
import kkbot.ui.Ui;

/**
 * Class representing the command when user inputs unmark command.
 *
 * @author AkkFiros
 */
public class UnmarkCommand extends Command {
    public static final String KEYWORD = "unmark";
    private int index; // index number of the task in taskList

    /**
     * Constructor for MarkCommand
     * @param inputNumber the number input by user for
     *                    the task they want to mark as done
     */
    public UnmarkCommand(int inputNumber) {
        super();
        index = inputNumber - 1;
    }

    /**
     * Returns a command for KKBot when user inputs "unmark".
     * @param tasks the list of tasks stored by KKBot
     * @param ui the ui object that governs what response is returned to the user
     * @param storage the storage object to save tasks to hard drive
     * @return the message shown when a task is marked as incomplete
     * @throws InvalidTaskException when the user input is wrong
     * @throws StorageException if there is an error reading from/writing to hard drive
     */
    public String execute(TaskList tasks, Ui ui, Storage storage)
            throws InvalidTaskException, StorageException {
        Task task = tasks.changeTaskStatus(index, false);
        storage.save(tasks);
        return ui.showTaskUndone(task);
    }
}
