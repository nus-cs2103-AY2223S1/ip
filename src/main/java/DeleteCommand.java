public class DeleteCommand extends Command {

    int taskNumber;

    public DeleteCommand(int taskNumber) {
        super();
        this.taskNumber = taskNumber;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task target = tasks.deleteTask(taskNumber);
        storage.save(tasks);
        ui.showDeleteTask(target, tasks.getSize());
    }

    public boolean isExit() {
        return false;
    }
}
