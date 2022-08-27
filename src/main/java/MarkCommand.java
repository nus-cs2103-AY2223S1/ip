public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    private static final String TASK_ADD = "Good Job! I will mark this task as done: ";
    public int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TaskMarkException, TaskNotFoundException {
        Task task = tasks.markTask(index);
        storage.update(tasks);
        displayCommand(ui, TASK_ADD, task, tasks.getStatus());
    }
}
