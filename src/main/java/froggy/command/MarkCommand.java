package froggy.command;

import java.util.Objects;

import froggy.task.Task;
import froggy.storage.Storage;
import froggy.task.TaskList;
import froggy.ui.Ui;

/**
 * A class which extends from the Command abstract class.
 * A MarkCommand object can be used to mark tasks as done or not done.
 */
public class MarkCommand extends Command {
    /* Whether to mark the object the task. */
    protected boolean isMark;
    /* Index of the item on the list to mark. */
    protected int index;

    /**
     * Creates a MarkCommand object.
     *
     * @param mark The string of whether it is mark or unmark.
     * @param index The index of the item to mark based on the output of the ListCommand.execute(...).
     */
    public MarkCommand(String mark, String index) {
        super(false);
        this.isMark = Objects.equals(mark, "mark");
        this.index = Integer.parseInt(index);
    }

    /**
     * Marks a task as done or not done.
     * Printing out hte appropriate UI when the task has been marked successfully.
     *
     * @param tasks The TaskList object containing all the tasks and CRUD methods to modify the tasks.
     * @param ui The Ui object capable of displaying user interface.
     * @param storage The storage object capable of doing write, load, open functionality.
     * @return the reply from the bot
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String messageToUser = generateAppropriateUiOutput(tasks, ui);
        storage.writeToFile(tasks);
        return messageToUser;
    }

    /**
     * Returns the appropriate message depending on whether it is a command to mark.
     *
     * @param tasks The TaskList object containing all the tasks and CRUD methods to modify the tasks.T
     * @param ui The Ui object capable of displaying user interface.
     * @return a message to be displayed to the user.
     */
    private String generateAppropriateUiOutput(TaskList tasks, Ui ui) {
        String messageToUser = "";
        if (this.isMark) {
            Task task = tasks.markTask(index);
            messageToUser += ui.showMarkStatus(task);
        } else {
            Task task = tasks.unmarkTask(index);
            messageToUser += ui.showUnmarkStatus(task);
        }
        return messageToUser;
    }
}
