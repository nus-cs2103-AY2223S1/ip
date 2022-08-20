package duke.commands;

import duke.DukeException;
import duke.storage.StorageFile;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Adds a new deadline to the task list.
 */
public class DeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Creates a new Deadline task.\n"
            + "Example: " + COMMAND_WORD;

    private final String description;
    private final String by;

    /**
     * Constructor for DeadlineCommand.
     *
     * @param description description of the new deadline
     * @param by date the deadline is to be completed by
     */
    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, StorageFile storage) throws DukeException {
        taskList.addDeadline(description, by);
        storage.save(taskList);
    }
}
