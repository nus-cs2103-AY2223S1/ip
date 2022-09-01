package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Events;
import duke.task.Task;

/**
 * Represents a command that is used to add an Event task into a tasklist, save the event
 * and print out the event that was added.
 */
public class AddEventsCommand extends Command {
    private String taskName;
    private LocalDate localDate;

    /**
     * Constructor for the AddEventCommand.
     *
     * @param task
     */
    public AddEventsCommand(String task) throws DateTimeParseException {
        int dateIndex = task.indexOf("at");
        this.taskName = task.substring(6, dateIndex);
        this.localDate = LocalDate.parse(task.substring(dateIndex + 3));
    }
    /**
     * Execute method that is used to add an Event task to a tasklist, save the event
     * and print out the event that was added through tasklist, ui and storage.
     *
     * @param taskList
     * @param ui
     * @param storage
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Task event = new Events(taskName, localDate);
        taskList.addTasks(event);
        storage.saveTasks(taskList);
        return event + " added!";
    }
}
