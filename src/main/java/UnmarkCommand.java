public class UnmarkCommand extends Command {
    private int taskIndex;

    public static String getFormat() {
        return "unmark <Integer>";
    }
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.unmark(this.taskIndex);
        ui.showUnmarkTask(task, tasks);
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
