public class ExitCommand extends Command {
    public ExitCommand() {
        super(true);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }
}
