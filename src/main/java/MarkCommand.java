public class MarkCommand extends Command {

    boolean isDone;
    int taskNum;

    public MarkCommand(boolean isDone, int taskNum) {
        this.isDone = isDone;
        this.taskNum = taskNum;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        if (isDone) {
            tasks.finishedTask(taskNum);
        } else {
            tasks.unfinishedTask(taskNum);
        }
    }

    @Override
    boolean isExit() {
        return false;
    }
}
