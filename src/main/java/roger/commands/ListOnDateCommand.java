package roger.commands;

import java.time.LocalDate;
import java.util.List;

import roger.storage.Storage;
import roger.tasks.TaskList;
import roger.tasks.Task;


/**
 * Encapsulates the command to list all tasks on a certain date.
 */
public class ListOnDateCommand extends ListCommand {
    protected LocalDate date;

    /**
     * Create a ListOnDateCommand.
     *
     * @param date The date to list tasks for.
     */
    public ListOnDateCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * List all tasks on the specified date.
     *
     * @param tasks The TaskList of the Roger program.
     * @param storage The storage to read and load to.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        StringBuilder response = new StringBuilder();
        response.append("Nephew have to do these things on " + date.toString() + ":\n");
        List<Task> filtered = tasks.filter(date);
        for (Task task: filtered) {
            response.append(String.valueOf(tasks.getTaskNum(task)) + ". " + task.toString() + "\n");
        }
        return response.toString();
    }
}
