public class GreetCommand extends Command {
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.showGreeting();
    }

    public boolean isExit() {
        return false;
    }
}
