public class ExitCommand extends Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.print("Cya!");
    }

    public boolean toClose() {
        return true;
    }
}
