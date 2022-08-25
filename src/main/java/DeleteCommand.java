public class DeleteCommand extends Command {
    private int position;
    public DeleteCommand(int position) {
        this.position = position;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, ListLoader storage) {
        if (tasks.isValidPosition(position)) {

            Task t = tasks.retrieveRank(position);
            tasks.delete(position);
            storage.deleteTask(t.summary());
            ui.showDelete(t, tasks.tasksLeft());
        } else {
            ui.showEmpty();
        }
    }
}
