public class DeleteCommand extends Command {
    private int taskNumber;
    
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task task = tasks.deleteTask(this.taskNumber - 1);
            storage.save(tasks);
            ui.showDeleteMessage(task, tasks.getSize());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(ui.getMissingTaskError(Keyword.DELETE, this.taskNumber));
        }
    }
    
    @Override
    public boolean isExit() {
        return false;
    }
}
