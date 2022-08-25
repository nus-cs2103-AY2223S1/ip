public class ByeCommand extends Command {
    public ByeCommand() {
        super(true);
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        ui.printGoodbye();
    }
}
