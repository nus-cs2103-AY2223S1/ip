public class ByeCommand extends Command{
    public ByeCommand() {
        super(true);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
    }
}
