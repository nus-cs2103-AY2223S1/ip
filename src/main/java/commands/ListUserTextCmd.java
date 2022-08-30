package commands;

import java.util.ArrayList;
import java.util.List;

import drivers.Storage;
import drivers.TaskList;
import drivers.Ui;

/**
 * Class to be executed when a list command is issued
 * by the user.
 */
public class ListUserTextCmd extends Command {
    /**
     * Executes the command and gives the appropriate
     * feedback to the user.
     * @param tasks TaskList containing all the tasks currently available.
     * @param ui Specifies how the program interacts with the user.
     * @param storage Stores and retrieves data from a local .txt file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.notifyUser("Here are your current tasks:");
        List<String> taskPrint = new ArrayList<>();
        tasks.fillTaskPrint(taskPrint);
        for (String task : taskPrint) {
            ui.notifyUser(task);
        }
    }
}
