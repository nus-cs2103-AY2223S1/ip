package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

import java.io.IOException;

public class DeleteCommand extends Command {

    protected int index;
    private final String MESSAGE = "\tNoted. I've remove this task: ";

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = taskList.deleteTask(index);
        storage.saveTaskList(taskList);
        String text = MESSAGE + "\n\t" + task.toString() +
                "\n" + taskList.displayNumTasks();
        ui.displayMessage(text);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}