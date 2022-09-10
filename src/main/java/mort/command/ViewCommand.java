package mort.command;

import java.time.LocalDate;

import mort.exception.MortException;
import mort.parser.Parser;
import mort.storage.Storage;
import mort.task.TaskList;
import mort.ui.Ui;

/**
 * Represents a command to view all tasks for a given date.
 */
public class ViewCommand extends Command {
    /** Date of task **/
    private String dateString;

    /**
     * Constructor to initialise view command with the date of a task.
     * @param dateString
     */
    public ViewCommand(String dateString) {
        this.dateString = dateString;
        assert !this.dateString.isBlank();
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MortException {
        LocalDate date = Parser.convertStringToDate(dateString);
        return ui.getViewMessage(tasks.viewSchedule(date), date);
    }
}
