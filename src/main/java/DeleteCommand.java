public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(Storage storage, Ui ui, TaskList taskList, int taskIndex) {
        super(storage, ui, taskList);
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute() throws DukeException {
        taskList.removeTask(taskIndex);
    }
}
