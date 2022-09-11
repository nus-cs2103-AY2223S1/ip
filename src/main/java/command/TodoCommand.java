package command;

import main.Storage;
import main.TaskList;
import main.Ui;
import task.Task;

public class TodoCommand extends Command{
    
    public TodoCommand(String commandArgs) {
        super(commandArgs);
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        
    }

    @Override
    public Task getTask() {
        // TODO Auto-generated method stub
        return null;
    }

}
