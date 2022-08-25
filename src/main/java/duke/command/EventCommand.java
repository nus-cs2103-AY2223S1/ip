package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.TaskList;

/**
 * Adds Event task in tasklist.
 */
public class EventCommand extends Command {
    private String description;
    private String at;

    /**
     * Creates an Event command object for execution in Duke class.
     * @param description details of task.
     * @param at date/time of when task occurs.
     */
    public EventCommand(String description, String at) {
        this.description = description;
        this.at = at;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Event event = new Event(description, at);
        taskList.addTask(event);
        ui.printMessage("+ Added this event:\n" + event + "\nNow you have " + taskList.listSize()
                + " tasks in the list\n");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
