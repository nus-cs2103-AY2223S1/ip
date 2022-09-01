public class InvalidCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showError("You've entered an invalid input!");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
