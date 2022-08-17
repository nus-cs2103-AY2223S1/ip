public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        ui.printUnmarkTask(taskList.unmarkTask(index));
        storage.saveLocalData(taskList.stringify());
    }
}
