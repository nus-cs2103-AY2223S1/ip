package duke;

public class UnmarkCommand extends Command {
    private int idx;

    public UnmarkCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.markAsNotDone(idx);
        storage.save(taskList);
    }
}
