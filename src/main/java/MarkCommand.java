public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";
    private int toMark;

    public MarkCommand(int toMark) {
        this.toMark = toMark - 1;
    }

    @Override
    public void execute(TaskList task, Ui ui, Storage storage) {
        Task taskToMark = task.getTask(toMark);
        task.markTask(this.toMark);
        ui.displayMarkTask(taskToMark);

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
