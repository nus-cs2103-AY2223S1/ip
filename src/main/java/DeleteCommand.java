public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private int taskIndex;

    public DeleteCommand(int taskNum) {
        super();
        this.taskIndex = taskNum - 1;
    }

    @Override
    public boolean isBye() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task deletedTask = taskList.deleteTask(this.taskIndex);
        ui.showTaskRemoved(deletedTask);
    }
}
