package command;

import duke.Constants;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import tasks.Event;

import java.time.LocalDateTime;

/**
 * Creates a new event task
 */
public class EventCommand extends Command {
    private String desc;
    private LocalDateTime at;

    /**
     * Constructor which creates a new event command with the taskname and date given
     *
     * @param desc
     * @param at
     */
    public EventCommand(String desc, LocalDateTime at) {
        super();
        this.desc = desc;
        this.at = at;
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
        Event event = new Event(this.desc, this.at);
        boolean isDuplicated = taskList.isTasksDuplicated(event);
        if (isDuplicated) {
            return Constants.ERROR_IF_DUPLICATED;
        } else {
            taskList.addTask(event);
            storage.writeFile(taskList.tasksToString());
            return ui.printAddTask(event, taskList.getSize());
        }
    }
}
