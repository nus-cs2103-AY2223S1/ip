public class MarkCommand extends Command {
    private boolean isMark;
    private int index;

    public MarkCommand(boolean isMark, int index) throws InvalidIndexException {
        if (index < 0 || index > TaskList.length() - 1) {
            throw new InvalidIndexException();
        }
        this.isMark = isMark;
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.markTask(this.isMark, this.index);
        storage.saveTaskFile(taskList);
    }
}
