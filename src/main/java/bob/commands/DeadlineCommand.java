package bob.commands;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import bob.BobException;
import bob.Deadline;
import bob.Storage;
import bob.TaskList;
import bob.Ui;

/**
 * DeadlineCommand class to handle "deadline" keyword
 */
public class DeadlineCommand extends Command {

    private String taskName;
    private String date;

    /**
     * Constructor for DeadlineCommand
     *
     * @param taskName name of deadline task
     * @param date deadline date
     */
    public DeadlineCommand(String taskName, String date) {
        super();
        this.taskName = taskName;
        this.date = date;
    }

    @Override
    public String executeCommand(TaskList taskList, Storage storage, Ui ui) throws BobException {
        try {
            Deadline newTask = new Deadline(this.taskName, LocalDate.parse(this.date));
            taskList.addTask(newTask);
            storage.save(taskList);
            return ui.displayAddedTask(taskList, newTask);
        } catch (DateTimeParseException e) {
            throw new BobException("please input date in yyyy-mm-dd format!");
        }
    }
}
