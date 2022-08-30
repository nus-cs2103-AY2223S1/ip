package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.ui.Ui;
import duke.task.TaskList;

public class ByeCommand implements Command {
    public static final String COMMAND_WORD = "bye";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exit from Duke. Example: " + COMMAND_WORD;

    private Storage storage;

    public ByeCommand(Storage storage) {
        this.storage = storage;
    }

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
