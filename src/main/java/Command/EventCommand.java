package Command;

import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;
import Tasks.Event;

import java.time.LocalDateTime;

/**
 * Creates a new event task
 */
public class EventCommand extends Command {
    private String desc;
    private LocalDateTime date;

    /**
     * Constructor which creates a new event command with the task name and date of event
     *
     * @param desc
     * @param date
     */
    public EventCommand(String desc, LocalDateTime date) {
        super();
        this.desc = desc;
        this.date = date;
    }

    /**
     * Creates a new event task and adds it to the current tasklist,
     * then saves it into the file and prints the output nicely
     * with the ui class so that user can understand what is happening.
     *
     * @param taskList which contains the current tasklist
     * @param ui which handles the user interface
     * @param storage which handles the saving and loading of file
     * @return string that will be printed in the UI
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Event event = new Event(this.desc, this.date);
        taskList.addTask(event);
        storage.writeFile(taskList.tasksToString());
        return ui.printAddTask(event, taskList.getSize());
    }
}
