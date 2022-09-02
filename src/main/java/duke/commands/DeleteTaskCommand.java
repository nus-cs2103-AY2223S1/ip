package duke.commands;

import duke.exceptions.TaskNotFoundException;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;
import duke.utils.Storage;

public class DeleteTaskCommand extends Command {

    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    private String index;

    public DeleteTaskCommand(Storage storage, Ui ui, TaskList tasks, String index) {
        this.storage = storage;
        this.ui = ui;
        this.tasks = tasks;
        this.index = index;
    }

    @Override
    public boolean execute() {
        try {
            Task deletedTask = tasks.deleteTask(Integer.parseInt(index) - 1);
            ui.showDeleteTaskResponse(deletedTask, tasks);
            storage.saveToFile(tasks.getList());
            return true;
        } catch (TaskNotFoundException e) {
            ui.showError(e);
            return false;
        } catch (NumberFormatException e) {
            ui.showInvalidFormatError(index);
            return false;
        }
    }
}
