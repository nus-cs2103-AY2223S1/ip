public class UnmarkCommand extends Command {
    private int indexToUnmark;

    public UnmarkCommand(int indexToUnmark) {
        super();
        this.indexToUnmark = indexToUnmark;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.getTask(indexToUnmark).markAsUndone();
        ui.showUnmarkMessage(taskList.getTask(indexToUnmark));
    }
}
