public class ExitCommand extends Command {
    protected void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.exit();
        storage.save(tasks.toString());
    }
    protected boolean isExit() {
        return true;
    }
}
