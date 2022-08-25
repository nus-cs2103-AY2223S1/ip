public class ListCommand extends Command {
    protected void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.displayTasks(tasks);
    }
}
