public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String undoneTask = tasks.markAsUndone(index);
        storage.save(tasks);
        ui.printString("Task marked as undone:\n" + undoneTask);
    }
}
