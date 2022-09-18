package bob.commands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import bob.BobException;
import bob.Storage;
import bob.TaskList;
import bob.Ui;

/**
 * FilterCommand class to handle "filter" keyword
 */
public class FilterCommand extends Command {

    private String date;

    /**
     * Constructor for FilterCommand
     *
     * @param date date to be filtered
     */
    public FilterCommand(String date) {
        super();
        this.date = date;
    }

    @Override
    public String executeCommand(TaskList taskList, Storage storage, Ui ui) throws BobException {
        try {
            String response = "here are your tasks on " + "'"
                    + LocalDate.parse(date).format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + "'";
            storage.save(taskList);
            return ui.displayTaskList(taskList.filterTask(date), response);
        } catch (DateTimeParseException e) {
            throw new BobException("please input date in yyyy-mm-dd format!");
        }
    }
}
