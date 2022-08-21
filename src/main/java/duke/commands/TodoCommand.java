package duke.commands;

import duke.DukeException;
import duke.storage.StorageFile;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Adds a new to-do to the task list.
 */
public class TodoCommand extends Command {

    public static final String COMMAND_WORD = "todo";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Creates a new Todo.\n"
            + "\tEx.: " + COMMAND_WORD + " <description>";

    private final String description;

    /**
     * Constructor for TodoCommand.
     *
     * @param description description of the to-do to add
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, StorageFile storage) throws DukeException {
        taskList.addTodo(description);
        storage.save(taskList);
    }
}
