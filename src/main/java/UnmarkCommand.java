public class UnmarkCommand extends Command {
    private int taskNumber;

    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            tasks.unmarkTask(this.taskNumber - 1);
            storage.save(tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(ui.getMissingTaskError(Keyword.UNMARK, this.taskNumber));
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
