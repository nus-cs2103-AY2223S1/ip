package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command to tag a <code>Task</code>.
 *
 * @author Derrick Khoo
 */
public class TagCommand extends Command {
    private String tag;
    private int index;

    /**
     * Constructs a command to tag a <code>Task</code>.
     *
     * @param tag the tag of the task, from user input
     * @param index the index of the task in the list of tasks
     */
    public TagCommand(String tag, int index) {
        this.tag = tag;
        this.index = index;
    }
    /**
     * Handles the command to tag a <code>Task</code>.
     *
     * @param storage  the <code>Storage</code> dealing with loading and saving tasks in a file
     * @param ui       the user interfaces that deals with user inputs
     * @param taskList the list of tasks
     * @throws DukeException if there is an error marking the task as done
     */
    public String handle(Storage storage, Ui ui, TaskList taskList) throws DukeException {
        Task t = taskList.getTask(index);
        t.tagTask(tag);
        return "Duke says:\n" + ui.formatMessage("Nice, I've tagged the task with:\n"
                + tag);
    }
}
