import java.io.IOException;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException{
            Storage.saveFile(taskList);
            ui.end();
    }
}
