public abstract class TaskCommand extends Command {
    public static final String TASK_ADD = "Got it. I've added this task";
    private Task task;

    public TaskCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        storage.update(tasks);
        displayCommand(ui, TASK_ADD, task, tasks.getStatus());
    }
}
