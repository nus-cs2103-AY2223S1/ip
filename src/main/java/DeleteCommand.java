public class DeleteCommand extends Command {
    int indexOfTask;
    public DeleteCommand(int indexOfTask) {
        super(false);
        this.indexOfTask = indexOfTask;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.deleteTask(indexOfTask);
        ui.deleteTask(tasks.getTask(indexOfTask));
        ui.displayNumberOfTasks(tasks.getNumberOfTasks());
        storage.writeToFile(tasks);
    }
}
