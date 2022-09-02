package duke.commands;

import duke.exceptions.TaskNotFoundException;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.utils.Storage;
import duke.ui.Ui;

public class MarkTaskCommand extends Command {

    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    private String index;

    public MarkTaskCommand(Storage storage, Ui ui, TaskList tasks, String index) {
        this.storage = storage;
        this.ui = ui;
        this.tasks = tasks;
        this.index = index;
    }

    @Override
    public boolean execute() {
        try {
            Task markedTask = tasks.markTask(Integer.parseInt(index) - 1);
            ui.showMarkTaskResponse(markedTask);
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
