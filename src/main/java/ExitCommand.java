public class ExitCommand extends Command {
    public ExitCommand() {}

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        ui.showOutput("Bye. Hope to see you again soon!\n");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
