public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public  void execute(TaskList tasks,Ui ui,Storage storage) {
        tasks.add(task);
        int total = tasks.totalSize();
        ui.showAdd(task,total);
        storage.store(tasks.getTaskList());
    }
}
