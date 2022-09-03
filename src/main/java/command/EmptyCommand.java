package command;

import exception.DukeException;
import javafx.scene.layout.VBox;
import storage.Storage;
import task.TaskList;
import ui.DialogBox;
import ui.Ui;

public class EmptyCommand extends Command {
    private static final String EMPTY_USER_INPUT_ERROR_MESSAGE = "Eh you never type anything leh?";
    private static final String EMPTY_STRING = "";

    public EmptyCommand(TaskList tasks, Ui ui) {
        super(tasks, EMPTY_STRING, ui);
    }

    @Override
    public void execute(VBox dialogContainer, DialogBox userDialog, Storage storage) throws DukeException {
        throw new DukeException(EMPTY_USER_INPUT_ERROR_MESSAGE);
    }
}
