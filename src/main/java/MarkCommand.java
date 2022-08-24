public class MarkCommand extends Command {

    private final Task task;

    public MarkCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        task.markAsDone();
        ui.MarkTask(task);
        storage.update(tasks.getTasks());
    }
}
