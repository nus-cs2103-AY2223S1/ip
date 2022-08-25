public class DeleteCommand extends Command {
    private int ind;
    DeleteCommand(int ind) {
        this.ind = ind;
    }
    protected void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.delTaskMessage(tasks.del(ind - 1),tasks);
        storage.save(tasks.toString());
    }
}
