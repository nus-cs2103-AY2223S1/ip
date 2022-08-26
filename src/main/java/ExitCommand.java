public class ExitCommand extends Command{

    public ExitCommand() {
        super();
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        ui.printFarewellMessage();
        storage.saveTaskList(taskList);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
