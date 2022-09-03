package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a bye command
 */
public class ByeCommand implements Command {
    public static final String COMMAND_WORD = "bye";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exit from Duke. Example: " + COMMAND_WORD;

    private Storage storage;

    /**
     * Constructor for a {@link ByeCommand}
     *
     * @param storage
     */
    public ByeCommand(Storage storage) {
        this.storage = storage;
    }

    /**
     * Executes a command
     *
     * @param taskList
     */
    @Override
    public String execute(TaskList taskList) {
        try {
            storage.saveFile(taskList);
        } catch (DukeException duke) {
            return "There is a problem exiting.";
        }
        return Ui.exitMessage();
    }
}
