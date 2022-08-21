public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        super(CommandType.DELETE);
        this.index = index - 1;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task deletedTask = tasks.getTask(index);
        tasks.deleteTask(index);
        ui.printDeleteTaskMessage(deletedTask, tasks.getTotalTasks());
    }
}
