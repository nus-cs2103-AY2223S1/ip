package duke.commands;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.ui.Ui;

/**
 * Represents the command to add a Deadline task to the list of tasks.
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    private String description;
    private String by;

    /**
     * Constructor for a DeadlineCommand.
     * @param description The description of the task.
     * @param by The date of the task.
     */
    public DeadlineCommand(String description, String by) {
        super();
        this.description = description;
        this.by = by;
    }

    /**
     * Adds a Deadline task to the list of tasks.
     * @param taskList List of tasks.
     * @param ui Shows the Task added and the total number of tasks on the list.
     * @param storage Saves the modified list of tasks.
     * @throws DukeException If there is an error saving the modified list of tasks.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Deadline deadline = new Deadline(description, by);
        taskList.addTask(deadline);
        storage.save(taskList);
        return ui.showTaskAdded(deadline) + ui.showNumberOfTasks(taskList.numTasks());
    }
}
