public class MarkCommand extends Command{
    private int number;

    public MarkCommand(int number) {
        this.number = number;
    }
    @Override
    public void execute(TaskList tasks,Ui ui,Storage storage) {
        Task task = tasks.getTaskList().get(this.number - 1);
        task.setCompleted();
        ui.showMarked(task);
        storage.store(tasks.getTaskList());
    }
}
