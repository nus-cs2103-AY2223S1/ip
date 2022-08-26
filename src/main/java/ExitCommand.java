import java.io.IOException;

public class ExitCommand extends Command {
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        storage.save(taskList.getTaskArrayList());
        ui.showGoodbye();
    }
}
