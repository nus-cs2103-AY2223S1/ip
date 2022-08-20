public class ByeCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showGoodbye();
    }
    
    @Override
    public boolean isExit() {
        return true;
    }
}
