package duke;

public class DeleteCommand extends Command {
    private int idx;

    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.deleteTask(idx);
        storage.save(taskList);
    }
}
