package command;

import java.io.IOException;

import exception.DukeException;
import exception.DukeIOException;
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

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeIOException{
        try {
            ui.chat("Goodbye!");
            storage.cleanUp();
        } catch (IOException e) {
            throw new DukeIOException(e.getLocalizedMessage());
        }
    }

    public Task getTask() {
        return null;
    }

}
