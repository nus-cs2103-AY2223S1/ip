import java.io.IOException;

public class ByeCommands extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException {
        storage.close();
        ui.printBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }


}

