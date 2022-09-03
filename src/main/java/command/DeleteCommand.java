package command;

import duke.Duke;
import exception.DukeException;
import javafx.scene.layout.VBox;
import storage.Storage;
import task.TaskList;
import ui.DialogBox;
import ui.Ui;

public class DeleteCommand extends Command {
    private static final String INVALID_TASK_NUMBER_ERROR_MESSAGE = "Eh, you got that task number meh?";
    private static final String INVALID_TASK_NUMBER_FORMAT_ERROR_MESSAGE = "Eh, you enter your task number correctly anot?";

    public DeleteCommand(TaskList tasks, String input, Ui ui) {
        super(tasks, input, ui);
    }

    @Override
    public void execute(VBox dialogContainer, DialogBox userDialog, Storage storage) throws DukeException {
        String taskIndexToDelete = input.substring(6).trim();
        try {
            int taskIndex = Integer.parseInt(taskIndexToDelete);
            if (!hasTaskIndex(taskIndex)) {
                throw new DukeException(INVALID_TASK_NUMBER_ERROR_MESSAGE);
            }
            ui.printDeletedTaskMessage(tasks.get(taskIndex - 1), dialogContainer, userDialog);
            tasks.remove(taskIndex - 1);
        } catch (NumberFormatException e) {
            throw new DukeException(INVALID_TASK_NUMBER_FORMAT_ERROR_MESSAGE);
        }
    }

    private boolean hasTaskIndex(int taskIndex) {
        return taskIndex - 1 < this.tasks.getSize() && taskIndex - 1 >= 0;
    }
}
