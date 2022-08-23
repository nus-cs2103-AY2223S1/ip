public class ByeCommand extends Command {
    public ByeCommand() {
        super();
        this.terminated = true;
    }

    @Override
    public void execCommand(TaskList list, Ui ui, Storage storage) {
        ui.showBye();
    }
}
