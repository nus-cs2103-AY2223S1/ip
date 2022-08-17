public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        ui.printMarkTask(taskList.markTask(index));
        storage.saveLocalData(taskList.stringify());
    }
}
