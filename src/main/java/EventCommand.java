public class EventCommand extends Command {
    private Event task;
    
    public EventCommand(Event task) {
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
