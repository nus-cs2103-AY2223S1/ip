public class ExitCommand implements Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.close();
        ui.showBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
