public class deleteCommand extends Command{
    private int taskNo;

    public deleteCommand (int taskNo){
        this.taskNo = taskNo;
    }

    @Override
    public void execute(TaskList tasks) {
        tasks.deleteTask(taskNo);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
