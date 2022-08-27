package duke;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public TaskList execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.markTask(index);
        Task temp = taskList.getTask(index);
        ui.displayTask(ui.MARKED, temp);
        storage.save(taskList, ui);
        return taskList;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
