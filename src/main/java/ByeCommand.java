public class ByeCommand extends Command {
    protected String commandLine;

    public ByeCommand(String commandLine) {
        this.commandLine = commandLine;
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList task, Ui ui, Storage storage) {
        ui.showGoodbye();
    }
}
