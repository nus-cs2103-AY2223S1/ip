public class MarkCommand extends Command {
    private int ind;
    private boolean toMark;

    MarkCommand(int ind, boolean toMark) {
        this.ind = ind;
        this.toMark = toMark;
    }
    protected void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task current = tasks.get(ind - 1);
        if (toMark) {
            current.doing();
            ui.markDone(current);
        } else {
            current.undo();
            ui.markUndone(current);
        }
        storage.save(tasks.toString());
    }
}
