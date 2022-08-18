public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        try {
            ui.printUnmarkTask(taskList.unmarkTask(index - 1));
            storage.saveLocalData(taskList.stringify());
        } catch (DukeException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }
}
