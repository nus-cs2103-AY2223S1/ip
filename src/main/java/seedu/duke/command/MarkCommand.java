package seedu.duke.command;

import seedu.duke.storage.Storage;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

import java.util.Objects;

/**
 * A class which extends from the Command abstract class.
 * A MarkCommand object can be used to mark tasks as done or not done.
 */
public class MarkCommand extends Command {
    /* Whether to mark the object the task. */
    protected boolean toMark;
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
        this.toMark = Objects.equals(mark, "mark");
        this.index = Integer.parseInt(index);
    }

    /**
     * Marks a task as done or not done.
     * Printing out hte appropriate UI when the task has been marked successfully.
     *
     * @param tasks The tasks object containing all the tasks and CRUD methods to modify the tasks.
     * @param ui The Ui object capable of displaying user interface.
     * @param storage The storage object capable of doing write, load, open functionality.
     * @return the reply from the bot
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String output = "";
        if (this.toMark) {
            Task task = tasks.markTask(index);
            output += ui.showMarkStatus(task);
        } else {
            Task task = tasks.unmarkTask(index);
            output += ui.showUnmarkStatus(task);
        }
        storage.writeToFile(tasks);
        return output;
    }
}
