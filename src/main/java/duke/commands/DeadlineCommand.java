package duke.commands;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.storage.exceptions.StorageException;
import duke.tasks.Deadline;
import duke.ui.Ui;

/**
 * Represents the command to add a Deadline task to the list of tasks.
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    public static final String DATE_INDICATOR = " /by ";
    private String description;
    private String date;

    /**
     * Constructor for a DeadlineCommand.
     * @param description The description of the task.
     * @param by The date of the task.
     */
    public DeadlineCommand(String description, String date) {
        super();
        this.description = description;
        this.date = date;
    }

    /**
     * Adds a Deadline task to the list of tasks.
     * @param taskList List of tasks.
     * @param ui Shows the Task added and the total number of tasks on the list.
     * @param storage Saves the modified list of tasks.
     * @return The message indicating that the Task has been added and the number of tasks on the list.
     * @throws StorageException If there is an error saving the modified list of tasks.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws StorageException {
        Deadline deadline = new Deadline(description, date);
        taskList.addTask(deadline);
        storage.save(taskList);
        return ui.showTaskAdded(deadline) + ui.showNumberOfTasks(taskList.getNumberOfTasks());
    }
}
