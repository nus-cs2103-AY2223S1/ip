public class UnmarkCommand extends Command {
    private final Task task;

    public UnmarkCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        task.markAsNotDone();
        ui.UnmarkTask(task);
        storage.update(tasks.getTasks());
    }
}
