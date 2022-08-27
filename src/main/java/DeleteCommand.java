public class DeleteCommand extends Command {

    private int taskNum;

    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.deleteTask(taskNum - 1);
    }

    @Override
    boolean isExit() {
        return false;
    }
}
