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
        int atIndex = task.indexOf("at");
        int taskIndex = 6;
        int dateIndex = atIndex + 3;
        this.taskName = task.substring(taskIndex, atIndex);
        this.localDate = LocalDate.parse(task.substring(dateIndex));
    }
    /**
     * Execute method that is used to add an Event task to a tasklist, save the event
     * and print out the event that was added through tasklist, ui and storage.
     *
    * @param taskList
     * @param archiveTaskList
     * @param storage
     * @param archiveStorage
     * @param ui
     */
    @Override
    public String execute(TaskList taskList, TaskList archiveTaskList, Storage storage,
                          Storage archiveStorage, Ui ui) {
        Task event = new Events(taskName, localDate);
        taskList.addTasks(event);
        storage.saveTasks(taskList);
        String response = event + " added";
        return response;
    }
}
