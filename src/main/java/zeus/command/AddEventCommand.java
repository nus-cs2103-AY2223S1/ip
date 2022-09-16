package zeus.command;

import java.time.LocalDateTime;

import zeus.exception.ZeusException;
import zeus.main.Storage;
import zeus.main.TaskList;
import zeus.main.Ui;
import zeus.task.Event;


/**
 * Handles adding Event.
 */
public class AddEventCommand extends Command {

    private String description;
    private String datetimeDescription;
    private LocalDateTime datetime;

    /**
     * Constructor of AddEventCommand class.
     *
     * @param description Description of Event.
     * @param datetimeDescription String that represents event date.
     */
    public AddEventCommand(String description, String datetimeDescription) {
        this.description = description;
        this.datetimeDescription = datetimeDescription;
    }

    /**
     * Constructor of AddEventCommand class.
     *
     * @param description Description of Event.
     * @param datetime LocalDateTime that represents event date.
     */
    public AddEventCommand(String description, LocalDateTime datetime) {
        this.description = description;
        this.datetime = datetime;
    }

    /**
     * Executes the command of adding the Event.
     *
     * @param taskList List of tasks.
     * @param ui The Ui.
     * @param storage The Storage.
     * @throws ZeusException If input is invalid.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws ZeusException {
        taskList.saveCurrentTaskListVersion();

        Event e;
        if (this.datetime == null) {
            e = new Event(description, this.datetimeDescription);
        } else {
            e = new Event(description, this.datetime);
        }
        taskList.addTask(e);
        ui.printAddTask(e, taskList.getSize());
        storage.saveTasksToDisk(taskList.getTaskList());
    }
}
