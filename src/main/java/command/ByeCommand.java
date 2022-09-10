package command;

import java.io.IOException;

import main.Storage;
import main.TaskList;
import main.Ui;
import task.Task;

public class ByeCommand extends Command{
    
    public ByeCommand(String commandArgs) {
        super(commandArgs);
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException{
        try {
            ui.chat("Goodbye!");
            storage.cleanUp();
        } catch (IOException e) {
            throw e;
        }
    }

    public Task getTask() {
        return null;
    }

}
