public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws DukeException {
        ui.printMarkTask(taskList.markTask(index - 1));
        storage.saveLocalData(taskList.stringify());
    }
}
