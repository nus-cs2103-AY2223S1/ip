import java.io.File;
import java.io.FileNotFoundException;

public class ClearCommand extends Command {

    @Override
    public void execute(TaskList taskList,
                        Storage storage, Ui ui) {
        taskList.clearStorage();
        ui.clearStorage();
    }
    
    @Override
    public boolean isRunning() {
        return true;
    }
}
