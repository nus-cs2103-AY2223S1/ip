package duke;

public class MarkCommand extends Command {
    private int idx;

    public MarkCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.markAsDone(idx);
        storage.save(taskList);
    }
}
