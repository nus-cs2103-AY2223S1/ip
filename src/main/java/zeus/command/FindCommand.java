package zeus.command;

import java.util.ArrayList;

import zeus.main.Storage;
import zeus.main.TaskList;
import zeus.main.Ui;
import zeus.task.Task;

/**
 * Class that represents a Command to search for tasks.
 */
public class FindCommand extends Command {

    private String s;

    public FindCommand(String s) {
        this.s = s;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> tasks = taskList.find(this.s);
        ui.printMatchingTasks(tasks);
    }
}
