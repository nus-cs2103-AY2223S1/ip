public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showListMessage(tasks.getSize());
        tasks.list();
    }
    
    @Override
    public boolean isExit() {
        return false;
    }
}
