package command;

import javafx.scene.layout.VBox;
import storage.Storage;
import task.TaskList;
import ui.DialogBox;
import ui.Ui;

/**
 * <h1>ListCommand class</h1>
 * Class that prints out the list of Tasks in the Ui
 */
public class ListCommand extends Command {
    private static final String LIST_TASKS_MESSAGE = "Boss ah, this one your tasks:";
    private static final String NO_TASKS_MESSAGE = "Boss, you got no task yet ah";

    /**
     * Creates the ListCommand
     *
     * @param tasks the list of Tasks.
     * @param input the input String from the user
     * @param ui the Ui object that handles the User Interface.
     */
    public ListCommand(TaskList tasks, String input, Ui ui) {
        super(tasks, input, ui);
    }

    /**
     * Prints out the list of Tasks in the Ui
     *
     * @param dialogContainer the VBox to add the dialog to.
     * @param userDialog the Dialog Box containing the user's input to be added to the Vbox.
     * @param storage the Storage to write the Tasks to an output file.
     */
    @Override
    public void execute(VBox dialogContainer, DialogBox userDialog, Storage storage) {
        ui.printTasks(tasks, LIST_TASKS_MESSAGE, NO_TASKS_MESSAGE, dialogContainer, userDialog);
    }
}
