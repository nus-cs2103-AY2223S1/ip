public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
