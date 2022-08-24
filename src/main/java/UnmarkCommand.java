public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";
    private int toUnmark;

    public UnmarkCommand(int toUnmark) {
        this.toUnmark = toUnmark - 1;
    }

    @Override
    public void execute(TaskList task, Ui ui, Storage storage) {
        Task taskToUnmark = task.getTask(toUnmark);
        task.unmarkTask(toUnmark);
        ui.displayUnmarkTask(taskToUnmark);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
