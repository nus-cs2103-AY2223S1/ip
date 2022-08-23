public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    private int taskIndex;

    public UnmarkCommand(int taskNum) {
        super();
        this.taskIndex = taskNum - 1;
    }

    @Override
    public boolean isBye() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task unmarkedTask = taskList.changeTaskStatus(this.taskIndex, false);
        ui.showTaskNotDone(unmarkedTask);
    }
}
