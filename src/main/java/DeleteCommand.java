public class DeleteCommand extends TaskListCommand {

    DeleteCommand(String cmd) {
        super(cmd);
    }

    @Override
    void specialisedFunction(TaskList tasks, Ui ui, Storage storage, int taskIndex) {
        Task removedTask = tasks.removeTask(taskIndex);
        ui.showRemoveTask(removedTask);
    }
}
