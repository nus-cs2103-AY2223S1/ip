package blink.command;

import blink.Storage;
import blink.TaskList;
import blink.Ui;
import blink.task.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.time.DateTimeException;

/**
 * Filter command to display all tasks of a specified date.
 */
public class FilterCommand extends Command{

    private LocalDate date;

    /**
     * Constructor for the FilterCommand which reads the input
     * and changes it into the appropriate date.
     *
     * @param input Information of date
     * @throws DateTimeException Thrown if input is not of suitable
     * date format (YYYY-MM-DD)
     */
    public FilterCommand(String input) {
        LocalDate date = LocalDate.parse(input);
        this.date = date;
    }

    /**
     * Runs the program by filtering through the TaskList, then the Ui
     * will show the tasks that meet the specified date input.
     *
     * @param tasks TaskList object of current Blink object
     * @param ui Ui object of current Blink object
     * @param storage Storage object of current Blink object
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> filteredList = tasks.filter(date);
        return ui.showFilter(filteredList, this.date);
    }

    /**
     * Filter command will not end the program.
     *
     * @return False
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
