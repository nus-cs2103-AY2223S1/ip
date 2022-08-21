public class DeadlineCommand extends Command {
    private Deadline task;
    
    public DeadlineCommand(Deadline task) {
        this.task = task;
    }
    
    @Override 
    public void execute(TaskList taskList,
                        Storage storage, Ui ui) {
        taskList.addTask(task);
        ui.addTask(task, taskList.length());
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
