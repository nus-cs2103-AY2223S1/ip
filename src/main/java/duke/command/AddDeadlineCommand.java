package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Deadlines;
import duke.task.Task;

/**
 * Represents a command that is used to add a deadline task into a tasklist, save the deadline
 * and print out the deadline that was added.
 */
public class AddDeadlineCommand extends Command {
    private String taskName;
    private LocalDate localDate;

    /**
     * Constructor for the AddDeadlineCommand.
     *
     * @param taskName
     * @param date
     */
    public AddDeadlineCommand(String taskName, String date) throws DateTimeParseException {
        this.taskName = taskName;
        this.localDate = LocalDate.parse(date);
    }

    /**
     * Execute method that is used to add a deadline task to a tasklist, save the deadline
     * and print out the deadline that was added through tasklist, ui and storage.
     *
     * @param taskList
     * @param ui
     * @param storage
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Task deadline = new Deadlines(taskName, localDate);
        taskList.addTasks(deadline);
        storage.saveTasks(taskList);
        return deadline + " added!";
//        ui.repeater(deadline + " added!");
    }
}
