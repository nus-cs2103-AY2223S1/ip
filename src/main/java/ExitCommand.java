public class ExitCommand extends Command {

    public ExitCommand() {
        super();
        this.isExit = true;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showGoodByeMessage();
//        storage.save(taskList.getTaskList());
    }
}
