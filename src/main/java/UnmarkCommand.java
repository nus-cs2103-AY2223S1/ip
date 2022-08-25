public class UnmarkCommand extends Command {
    private int position;

    public UnmarkCommand(int position) {
        this.position = position;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, ListLoader storage) {
        if (tasks.isValidPosition(position)) {

            Task t = tasks.retrieveRank(position);
            storage.unmarkTask(t.summary());
            tasks.unmark(position);
            ui.showUnmark(t);
        } else {
            ui.showEmpty();
        }

    }
}
