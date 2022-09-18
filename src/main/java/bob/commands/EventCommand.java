package bob.commands;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import bob.BobException;
import bob.Event;
import bob.Storage;
import bob.TaskList;
import bob.Ui;



/**
 * DeadlineCommand class to handle "event" keyword
 */
public class EventCommand extends Command {

    private String taskName;
    private String date;

    /**
     * Constructor for EventCommand
     *
     * @param taskName name of event task
     * @param date event date
     */
    public EventCommand(String taskName, String date) {
        super();
        this.taskName = taskName;
        this.date = date;
    }

    @Override
    public String executeCommand(TaskList taskList, Storage storage, Ui ui) throws BobException {
        try {
            Event newTask = new Event(this.taskName, LocalDate.parse(this.date));
            taskList.addTask(newTask);
            storage.save(taskList);
            return ui.displayAddedTask(taskList, newTask);
        } catch (DateTimeParseException e) {
            throw new BobException("please input date in yyyy-mm-dd format!");
        }
    }
}
