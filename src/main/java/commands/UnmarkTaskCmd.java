package commands;

import drivers.Storage;
import drivers.TaskList;
import drivers.Ui;
import exceptions.TumuException;
import tasks.Task;

/**
 * Class to be executed when a deadline command is issued
 * by the user.
 */
public class UnmarkTaskCmd extends Command {
    private final int taskIndex;

    /**
     * Constructor for the UnmarkTaskCmd class.
     * @param body The rest of the instruction issued by the user after command.
     */
    public UnmarkTaskCmd(String body) throws NumberFormatException {
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
        Task task = tasks.unmarkTask(taskIndex);
        String output = "";
        if (task != null) {
            output += ui.notifyUser("Alright, I have unmarked this task:\n\t" + task);
        }
        saveUserTasks(storage, tasks, ui);
        return output;
    }
}
