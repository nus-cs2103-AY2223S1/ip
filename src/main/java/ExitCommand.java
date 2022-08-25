public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, ListLoader storage) {
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
