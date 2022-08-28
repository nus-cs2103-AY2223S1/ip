package tuna.command;

import java.time.LocalDate;

import tuna.Storage;
import tuna.TaskList;
import tuna.Ui;

/**
 * Represents a List command. A ListCommand object contains a boolean indicating if a date is provided and the
 * given date.
 */
public class ListCommand extends Command {
    /** Date provided by the user */
    private LocalDate date;

    /**
     * Creates a ListCommand object.
     *
     * @param date Date to be compared with.
     */
    public ListCommand(LocalDate ... date) {
        super(CommandType.LIST);
        if (date.length == 1) {
            this.date = date[0];
        } else {
            this.date = null;
        }
    }

    /**
     * Executes the list command, either listing all tasks, or tasks that fall on the given date, depending on
     * the boolean provided during initialisation.
     *
     * @param tasks TaskList object.
     * @param ui Ui object.
     * @param storage Storage object.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (date != null) {
            return ui.listTasksDueOnDateMessage(date, tasks.listTasks(date));
        } else {
            return ui.listTasksMessage(tasks.listTasks());
        }
    }
}
