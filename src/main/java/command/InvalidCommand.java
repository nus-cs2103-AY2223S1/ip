package command;

import duke.Duke;
import exception.DukeException;
import javafx.scene.layout.VBox;
import storage.Storage;
import task.TaskList;
import ui.DialogBox;
import ui.Ui;

public class InvalidCommand extends Command {
    private static final String DEFAULT_ERROR_MESSAGE = "What talking you";

    public InvalidCommand(TaskList tasks, Ui ui) {
        super(tasks, "", ui);
    }

    @Override
    public void execute(VBox dialogContainer, DialogBox userDialog, Storage storage) throws DukeException {
        throw new DukeException(DEFAULT_ERROR_MESSAGE);
    }
}
