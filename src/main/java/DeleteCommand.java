public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private static final String TASK_DELETE = "Noted. I've removed this task:";
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TaskNotFoundException {
        Task task = tasks.deleteTask(index);
        storage.update(tasks);
        displayCommand(ui, TASK_DELETE, task, tasks.getStatus());
    }
}
