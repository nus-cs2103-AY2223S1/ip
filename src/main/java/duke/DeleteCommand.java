package duke;

public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public TaskList execute(TaskList taskList, Ui ui, Storage storage) {
        Task temp = taskList.getTask(index);
        taskList.removeTask(index);
        storage.save(taskList, ui);
        ui.displayTask(ui.DELETED, temp);
        ui.showTotalTasks(taskList);
        return taskList;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
