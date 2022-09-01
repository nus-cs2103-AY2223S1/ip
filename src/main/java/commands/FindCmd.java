package commands;

import java.util.List;

import drivers.Storage;
import drivers.TaskList;
import drivers.Ui;
import exceptions.TumuException;
import tasks.Task;

/**
 * Class to be executed when a find command is issued
 * by the user.
 *
 * Finds all entries that contain the keyword inside the task description.
 */
public class FindCmd extends Command {
    private final String body;

    /**
     * Constructor for the FindCmd class.
     * @param body The rest of the instruction issued by the user after command.
     */
    public FindCmd(String body) {
        this.body = body;
    }

    /**
     * Finds entries with the keyword in body, retrieves it and sends it back to the user.
     * @param tasks TaskList containing all the tasks currently available.
     * @param ui Specifies how the program interacts with the user.
     * @param storage Stores and retrieves data from a local .txt file.
     * @throws TumuException Parent exception for the program.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws TumuException {
        List<Task> wantedTasks = tasks.tasksContain(body);
        String output = "";
        output += ui.notifyUser("Here are the matching tasks in your list:");
        int counter = 1;
        for (Task task : wantedTasks) {
            output += ui.notifyUser(String.format("%d. %s", counter, task));
            counter++;
        }
        return output;
    }
}
