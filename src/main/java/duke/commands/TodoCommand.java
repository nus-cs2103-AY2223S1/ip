package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;
import duke.task.ToDo;

public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";

    private String userDescription;
    public TodoCommand(String userDescription) {
        this.userDescription = userDescription;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task t = new ToDo(userDescription);
        tasks.addTask(t);
        storage.save(tasks.getTaskList());
        ui.showAddTask(t, tasks);
    }
}
