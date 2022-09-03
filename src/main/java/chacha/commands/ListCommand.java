package chacha.commands;

import java.util.ArrayList;

import chacha.Storage;
import chacha.TaskList;
import chacha.Ui;
import chacha.tasks.Task;

public class ListCommand extends Command {
    
    @Override
    public void execute(ArrayList<Task> taskList, Ui ui) {
        ui.printList(taskList);
    }

}
