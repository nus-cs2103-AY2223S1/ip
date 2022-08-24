public class ByeCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    @Override
    public void execute(TaskList task, Ui ui, Storage storage) {
        storage.saveTaskToFile(task.getListOfTasks());
        ui.displayBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
