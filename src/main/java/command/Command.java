package command;

import exception.DukeException;
import javafx.scene.layout.VBox;
import storage.Storage;
import task.TaskList;
import ui.DialogBox;
import ui.Ui;

/**
 * <h1>Command class</h1>
 * Filters out the commandType and runs the executes the appropriate
 * command based on the input commandType.
 */
public abstract class Command {
    protected TaskList tasks;
    protected String input;
    protected Ui ui;

    /**
     * Creates the Command class.
     *
     * @param tasks the list of Tasks.
     * @param input the input String from the user.
     * @param ui the Ui object that handles the User Interface.
     */
    public Command(TaskList tasks, String input, Ui ui) {
        this.tasks = tasks;
        this.input = input;
        this.ui = ui;
    }

    /**
     * Executes the command according to the Command type
     *
     * @param dialogContainer the VBox to add the dialog to.
     * @param userDialog the Dialog Box containing the user's input to be added to the Vbox.
     * @param storage the Storage to write the Tasks to an output file.
     * @throws DukeException the exception to be thrown when Duke encounters an issue.
     */
    public abstract void execute(VBox dialogContainer, DialogBox userDialog, Storage storage) throws DukeException;
}

