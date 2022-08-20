public class MarkCommand extends Command {
    private int taskIndex;

    public static String getFormat() {
        return "mark <Integer>";
    }
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.mark(this.taskIndex);
        ui.showMarkTask(task, tasks);
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
