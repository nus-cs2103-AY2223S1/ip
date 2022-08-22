public class MarkCommand extends Command{
    private int num;

    public MarkCommand(int num) {
        this.num = num;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.mark(num);
        ui.mark(tasks, num);
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
