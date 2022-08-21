public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, 
                        Storage storage, Ui ui) {
        ui.list(taskList);
    }
    
    @Override
    public boolean isRunning() {
        return true;
    }
}
