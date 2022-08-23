package roger.commands;

import roger.Storage;
import roger.TaskList;
import roger.Ui;
import roger.commands.ListCommand;
import roger.tasks.Task;

import java.time.LocalDate;
import java.util.List;

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
     * @param ui The Ui used.
     * @param storage The storage to read and load to.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.show("Nephew have to do these things on " + date.toString() + ":");
        List<Task> filtered = tasks.filter(date);
        for (Task task: filtered) {
            ui.show(String.valueOf(tasks.getTaskNum(task)) + ". " + task.toString());
        }
    }
}
