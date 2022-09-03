package command;

import exception.DukeException;
import javafx.scene.layout.VBox;
import storage.Storage;
import task.TaskList;
import ui.DialogBox;
import ui.Ui;

public class ListCommand extends Command {
    private static final String LIST_TASKS_MESSAGE = "Boss ah, this one your tasks:";
    private static final String NO_TASKS_MESSAGE = "Boss, you got no task yet ah";


    public ListCommand(TaskList tasks, String input, Ui ui) {
        super(tasks, input, ui);
    }

    @Override
    public void execute(VBox dialogContainer, DialogBox userDialog, Storage storage) {
        ui.printTasks(tasks, LIST_TASKS_MESSAGE, NO_TASKS_MESSAGE, dialogContainer, userDialog);
    }
}
