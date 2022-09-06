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

    private String stringToMatch;

    public FindCommand(String stringToMatch) {
        this.stringToMatch = stringToMatch;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> tasks = taskList.findMatchingTasks(this.stringToMatch);
        ui.printMatchingTasks(tasks);
    }
}
