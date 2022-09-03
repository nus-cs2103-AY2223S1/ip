package command;

import exception.DukeException;
import javafx.scene.layout.VBox;
import task.Task;
import task.TaskList;
import task.Todo;
import ui.DialogBox;
import ui.Ui;

public abstract class AddTaskCommand extends Command {
    protected static final String EMPTY_TASK_NAME_ERROR_MESSAGE = "Eh you never added your task name";
    protected static final String EMPTY_STRING = "";

    public AddTaskCommand(TaskList tasks, String input, Ui ui) {
        super(tasks, input, ui);
    }

    protected abstract boolean isEmptyDescription(String[] splitTask);

    protected void addTask(Task input) {
        tasks.add(input);
    }
}
