package duke;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public TaskList execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(task);
        ui.displayTask(ui.ADDED, task);
        ui.showTotalTasks(taskList);
        storage.save(taskList, ui);
        return taskList;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
