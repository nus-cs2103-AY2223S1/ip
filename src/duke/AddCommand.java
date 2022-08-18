package duke;

public class AddCommand extends Command {

    protected Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(Ui ui, TaskList taskList) {
        taskList.addTask(task);
        ui.addSuccess(task, taskList.numOfTask());
    }
}
