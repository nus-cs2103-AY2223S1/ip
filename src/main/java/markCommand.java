public class markCommand extends Command{
    private int taskNo;

    public markCommand (int taskNo){
        this.taskNo = taskNo;
    }

    @Override
    public void execute(TaskList tasks) {
        tasks.markTask(taskNo);;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
