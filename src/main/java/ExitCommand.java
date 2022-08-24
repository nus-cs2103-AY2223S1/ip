public class ExitCommand extends Command {

    public ExitCommand(String cmd) {
        super(cmd);
    }

    @Override
    void execute(Ui ui, TaskList taskList) throws DukeException {
        ui.showExitMsg();
    }
}
