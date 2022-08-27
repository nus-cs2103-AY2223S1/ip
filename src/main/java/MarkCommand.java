public class MarkCommand extends Command {
    private int taskNumber;
    
    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            tasks.markTask(this.taskNumber - 1);
            storage.save(tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(ui.getMissingTaskError(Keyword.MARK, this.taskNumber));
        }
    }
    
    @Override
    public boolean isExit() {
        return false;
    }
}
