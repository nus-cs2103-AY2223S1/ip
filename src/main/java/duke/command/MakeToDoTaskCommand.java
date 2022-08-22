package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.ToDo;

public class MakeToDoTaskCommand extends Command {
    private String description;

    public MakeToDoTaskCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ToDo newTask = new ToDo(this.description);
        taskList.addTask(newTask);
        ui.showTaskAdded(newTask, taskList.getTaskListLength());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
