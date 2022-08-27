public class ExitCommand extends Command {
    public void execute (TaskList tasks, Ui ui, Storage storage) {
        ui.leaveChat();
    };

    public boolean isExit() {
        return true;
    };
}
