public class ExitCommand extends Command {
    ExitCommand() {
        super();
    }

    void execute(TaskList tasks, Ui ui, Storage storage) {
        this.isExit = true;
        ui.showMessage("Bye. Hope to see you again soon!");
    }
}
