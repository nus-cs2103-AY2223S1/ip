package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Deletes a command from the tasklist.
 */
public class DeleteCommand extends Command {

    protected int index;
    private final String MESSAGE = "Noted. I've remove this task: ";

    /**
     * Constructs a delete command
     *
     * @param index index of the task to be deleted
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = taskList.deleteTask(index);
        storage.saveTaskList(taskList);
        String text = MESSAGE + "\n" + task.toString() +
                "\n" + taskList.displayNumTasks();
        return ui.displayMessage(text);
    }


}