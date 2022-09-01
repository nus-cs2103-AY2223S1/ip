package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * A command that is used to add a Deadline Task.
 *
 * @author Lee Ian Ee
 * @version CS2103T AY22/23 Sem 1
 */

public class DeadlineCommand extends Command {
    private String description;
    private String by;

    /**
     * Constructor for DeadlineCommand.
     * @param description Description of the Deadline Task.
     * @param by When the Deadline Task has to be completed by.
     */
    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Adds a Deadline Task to list and returns the toString of the Task added.
     * @param list TaskList containing the list of tasks.
     * @param storage Storage that loads and saves to the save file.
     * @return toString of the Task added.
     */
    @Override
    public String execute(TaskList list, Storage storage) {
        Task task = list.addDeadline(description, by);
        storage.writeToFile(list);
        return Ui.addTask(task);
    }
}
