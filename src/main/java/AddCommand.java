public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        ui.showAddTask(task, tasks.getNumberOfTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
