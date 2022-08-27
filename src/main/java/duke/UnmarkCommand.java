package duke;

public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public TaskList execute(TaskList taskList, Ui ui, Storage storage) {

        taskList.unmarkTask(index);
        Task temp = taskList.getTask(index);

        ui.displayTask(ui.UNMARKED, temp);
        storage.save(taskList, ui);
        return taskList;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
