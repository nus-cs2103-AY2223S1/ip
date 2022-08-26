public class MarkDoneCommand extends Command {
    private int index;

    public MarkDoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.markDone(index);
        ui.showMarkDone(tasks.getTask(index));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

