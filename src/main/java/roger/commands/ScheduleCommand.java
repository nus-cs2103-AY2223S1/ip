package roger.commands;

import java.time.LocalDate;
import java.util.List;

import roger.storage.Storage;
import roger.tasks.Task;
import roger.tasks.TaskList;

/**
 * Encapsulates the command to show the user's schedule on a given date.
 */
public class ScheduleCommand extends Command {
    protected LocalDate fromDate;
    protected LocalDate toDate;

    /**
     * Create a ScheduleCommand.
     *
     * @param fromDate The date to list tasks from.
     * @param toDate The date to list tasks to.
     */
    public ScheduleCommand(LocalDate fromDate, LocalDate toDate) {
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    /**
     * Return the user's schedule in the given period.
     *
     * @param tasks The TaskList of the Roger program.
     * @param storage The storage to read and load to.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        List<Task> filtered = tasks.filter(fromDate, toDate);
        if (filtered.isEmpty()) {
            return "No tasks in your schedule, nephew very free can shake leg.";
        }

        StringBuilder response = new StringBuilder();
        response.append("Nephew's schedule for " + fromDate.toString());
        response.append(" - " + toDate.toString() + ":\n");
        for (Task task: filtered) {
            response.append(tasks.getTaskNum(task) + ". " + task.toString() + "\n");
        }
        return response.toString();
    }
}
