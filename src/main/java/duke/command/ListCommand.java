package duke.command;

import java.time.LocalDate;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a List command. A ListCommand object contains a boolean indicating if a date is provided and the
 * given date.
 */
public class ListCommand extends Command {

    /** Boolean value indicating if a date is provided */
    private boolean isDueListCommand;
    /** Date provided by the user */
    private LocalDate date;

    /**
     * Creates a ListCommand object.
     *
     * @param isDueListCommand Indicates if a date is provided.
     * @param date Date to be compared with.
     */
    public ListCommand(boolean isDueListCommand, LocalDate date) {
        super(CommandType.LIST);
        this.isDueListCommand = isDueListCommand;
        this.date = date;
    }

    /**
     * Creates a ListCommand object.
     */
    public ListCommand() {
        super(CommandType.LIST);
        this.isDueListCommand = false;
    }

    /**
     * Executes the list command, either listing all tasks, or tasks that fall on the given date, depending on
     * the boolean provided during initialisation.
     *
     * @param tasks TaskList object.
     * @param ui Ui object.
     * @param storage Storage object.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (isDueListCommand) {
            ui.printListTasksDueOnDateMessage(date);
            tasks.listTasks(date);
        } else {
            ui.printListTasksMessage();
            tasks.listTasks();
        }
    }
}
