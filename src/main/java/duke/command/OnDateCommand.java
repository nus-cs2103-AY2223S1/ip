package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.Collectors;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Checks if there are any tasks on the current date.
 *
 * @author Bryan Ng Zi Hao
 */
public class OnDateCommand extends Command {
    private LocalDate date;

    /**
     * Constructor for OnDateCommand.
     *
     * @param date The date which user checks if any task is on.
     */
    public OnDateCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * Lists the tasks that are due on that date provided.
     *
     * @param ui The interactions with user being used.
     * @param storage The storage which the data is being stored.
     * @param taskList The list of tasks to be updated in the storage.
     * @return String representation of Duke's reply.
     * @throws DukeException There is an error in execution.
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        String formattedDate = date.format(DateTimeFormatter.ofPattern("E, d MMM yyyy"));
        ArrayList<Task> filteredTasks = taskList.getTasks().stream().filter(task -> task.isOn(date))
                .collect(Collectors.toCollection(ArrayList::new));
        if (filteredTasks.size() > 0) {
            String output = String.format("Here are the tasks you have on %s:\n", formattedDate);
            for (int i = 0; i < filteredTasks.size(); i++) {
                output += (i + 1) + ". " + filteredTasks.get(i) + "\n";
            }
            return output;
        } else {
            return String.format("You have no tasks on %s", formattedDate);
        }
    }

    /**
     * Checks if this command will result in the bot to stop running.
     *
     * @return a boolean value.
     */
    @Override
    public boolean isRunning() {
        return true;
    }
}
