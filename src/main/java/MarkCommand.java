public class MarkCommand extends Command {
    private int position;
    public MarkCommand(int position) {
        this.position = position;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, ListLoader storage) {
        if (tasks.isValidPosition(position)) {
            Task t = tasks.retrieveRank(position);
            storage.markTask(t.summary());
            tasks.mark(position);
            ui.showMark(t);
        } else {
            ui.showEmpty();
        }

    }
}
