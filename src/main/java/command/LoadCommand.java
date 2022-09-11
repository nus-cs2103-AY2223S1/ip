package command;

import java.io.FileNotFoundException;

import exception.InvalidCommandException;
import exception.InvalidDateException;
import exception.MissingArgumentException;
import main.Storage;
import main.TaskList;
import main.Ui;
import task.Task;

public class LoadCommand extends Command{
    
    public LoadCommand(String commandArgs) {
        super(commandArgs);
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidDateException, InvalidCommandException, MissingArgumentException, FileNotFoundException{
        storage.loadLog();
       
    }

    @Override
    public Task getTask() {
        return null;
    }
}
