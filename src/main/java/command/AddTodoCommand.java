package command;

import exception.DukeException;
import javafx.scene.layout.VBox;
import storage.Storage;
import task.TaskList;
import task.Todo;
import ui.DialogBox;
import ui.Ui;

public class AddTodoCommand extends AddTaskCommand {
    public AddTodoCommand(TaskList tasks, String input, Ui ui) {
        super(tasks, input, ui);
    }

    @Override
    public void execute(VBox dialogContainer, DialogBox userDialog, Storage storage) throws DukeException {
        String[] splitTodo = input.split(" ");
        if (isEmptyDescription(splitTodo)) {
            throw new DukeException(EMPTY_TASK_NAME_ERROR_MESSAGE);
        }
        Todo todo = new Todo(splitTodo[1], false);
        this.addTask(todo);
        ui.printAddedTaskMessage(todo, dialogContainer, userDialog);
        ui.printTaskCountMessage(tasks, dialogContainer);
    }

    @Override
    protected boolean isEmptyDescription(String[] splitTodo) {
        return splitTodo[1].trim().equals(EMPTY_STRING);
    }
}
