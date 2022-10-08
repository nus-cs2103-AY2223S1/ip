package command;

import exception.DukeException;
import javafx.scene.layout.VBox;
import storage.Storage;
import task.TaskList;
import task.Todo;
import ui.DialogBox;
import ui.Ui;

/**
 * <h1>AddTodoCommand class</h1>
 * Class that adds the generated Todo to the TaskList
 */
public class AddTodoCommand extends AddTaskCommand {
    /**
     * Creates the AddTodoCommand
     *
     * @param tasks the list of Tasks.
     * @param input the input String from the user
     * @param ui the Ui object that handles the User Interface.
     */
    public AddTodoCommand(TaskList tasks, String input, Ui ui) {
        super(tasks, input, ui);
    }

    /**
     * Adds the Todo to the TaskList
     *
     * @param dialogContainer the VBox to add the dialog to.
     * @param userDialog the Dialog Box containing the user's input to be added to the Vbox.
     * @param storage the Storage to write the Tasks to an output file.
     * @throws DukeException Throws an error message indicating that the user's input is invalid
     */
    @Override
    public void execute(VBox dialogContainer, DialogBox userDialog, Storage storage) throws DukeException {
        String[] splitTodo = input.split(" ", 2);
        if (isEmptyDescription(splitTodo)) {
            throw new DukeException(EMPTY_TASK_NAME_ERROR_MESSAGE);
        }
        Todo todo = new Todo(splitTodo[1].trim(), false);
        this.addTask(todo);
        ui.printAddedTaskMessage(todo, dialogContainer, userDialog);
        ui.printTaskCountMessage(tasks, dialogContainer);
    }

    @Override
    protected boolean isEmptyDescription(String[] splitTodo) {
        return splitTodo.length == 1;
    }
}
