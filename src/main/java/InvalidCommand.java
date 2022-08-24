public class InvalidCommand extends Command {
    public InvalidCommand() {
        super(false);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showError("I'm sorry, but I don't know what that means :-(");
    }
}
