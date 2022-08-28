public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int num) {
        this.index = num - 1;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.markAsDone(index);
        ui.mark(taskList, index);
        storage.save(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
