package commands;

import drivers.Storage;
import drivers.TaskList;
import drivers.Ui;
import exceptions.TumuException;
import tasks.Task;

/**
 * Class to be executed when a delete command is
 * issued by the user.
 */
public class DeleteCmd extends Command {
    private final int taskIndex;

    /**
     * Constructor for the DeleteCmd class.
     * @param body The rest of the instruction issued by the user after command.
     * @throws NumberFormatException Thrown when the parsed information of task cannot be converted into
     * an integer.
     */
    public DeleteCmd(String body) throws NumberFormatException {
        taskIndex = Integer.parseInt(body);
    }

    /**
     * Executes the command and gives the appropriate
     * feedback to the user.
     * @param tasks TaskList containing all the tasks currently available.
     * @param ui Specifies how the program interacts with the user.
     * @param storage Stores and retrieves data from a local .txt file.
     * @throws TumuException Parent exception for the program.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws TumuException {
        Task removedTask = tasks.deleteTask(taskIndex);
        String output = "";
        if (removedTask != null) {
            output += ui.notifyUser("Alright, I have removed this task for you:\n\t\t" + removedTask);
            output += ui.notifyUser(String.format("You have %d task(s) in the list.", tasks.getListSize()));
        }
        saveUserTasks(storage, tasks, ui);
        return output;
    }
}
