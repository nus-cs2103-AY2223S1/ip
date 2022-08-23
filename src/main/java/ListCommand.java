public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    @Override
    public boolean isBye() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showAllTasks(taskList);
    }
}
