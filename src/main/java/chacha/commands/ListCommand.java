package chacha.commands;



import chacha.Storage;
import chacha.TaskList;
import chacha.Ui;


public class ListCommand extends Command {
    
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printList(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
