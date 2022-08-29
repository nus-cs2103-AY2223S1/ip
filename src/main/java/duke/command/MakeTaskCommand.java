package duke.command;

import duke.DukeException;
import duke.operations.Storage;
import duke.operations.TaskList;
import duke.operations.Ui;
import duke.task.Task;

abstract class MakeTaskCommand extends Command {

    private final String detail;

    MakeTaskCommand(String detail) {
        this.detail = detail;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task newTask = makeNewTask(detail, ui);
        tasks.addTask(newTask);
        ui.showNewTask();
        System.out.println(newTask);
        storage.updateSave(tasks);
    }

    abstract Task makeNewTask(String detail, Ui ui) throws DukeException;
}
