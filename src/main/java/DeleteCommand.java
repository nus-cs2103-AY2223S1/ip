public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    private int toDelete;

    public DeleteCommand(int toDelete) {
        this.toDelete = toDelete - 1;
    }

    @Override
    public void execute(TaskList task, Ui ui, Storage storage) {
        Task taskToDelete = task.getTask(toDelete);
        task.deleteTask(toDelete);
        ui.displayDeleteTask(taskToDelete);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
