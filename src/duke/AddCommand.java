package duke;

import duke.exceptions.DukeException;

public class AddCommand extends Command {

    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(Ui ui, TaskList taskList) throws DukeException {
        taskList.addTask(task);
        ui.addSuccess(task, taskList.numOfTask());
    }
}
