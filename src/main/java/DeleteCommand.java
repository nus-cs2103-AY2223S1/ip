public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws DukeException {
        ui.printDeleteTask(taskList.deleteTask(index - 1));
        ui.printSizeOfList(taskList.size());
        storage.saveLocalData(taskList.stringify());
    }
}
