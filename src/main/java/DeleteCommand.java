public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        Task task = tasks.getTask(this.taskIndex);
        tasks.removeTask(task);
        storage.writeAllTasksToFile(tasks);
        ui.showRemoveTaskMessage(task, tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
