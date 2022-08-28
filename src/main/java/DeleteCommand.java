public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int num) {
        this.index = num - 1;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task tempTask = taskList.get(index);
        taskList.delete(index);
        ui.delete(tempTask, taskList.size());
        storage.save(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
