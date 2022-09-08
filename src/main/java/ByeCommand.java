import java.io.IOException;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DrakeException {
        storage.close();
        ui.printBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
