public class ByeCommand extends Command {

    public ByeCommand(String args) {
        super(args);
    }

    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) throws TedException {
        ui.exit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
