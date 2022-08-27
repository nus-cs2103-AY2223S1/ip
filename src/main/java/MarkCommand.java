public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String doneTask = tasks.markAsDone(index);
        storage.save(tasks);
        ui.printString("Task marked as done:\n" + doneTask);
    }
}
