public class GoodbyeCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.goodbye();
    }

    public boolean isExit() {
        return true;
    }
}
