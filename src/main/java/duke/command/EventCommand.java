package duke.command;

import duke.task.Event;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * EventCommand adds an Event to tasks.
 */
public class EventCommand extends Command {
    private String eventDescription;
    private String at;

    /**
     * Constructor for EventCommand.
     * @param eventDescription event description
     * @param at when the event is occurring.
     */
    public EventCommand(String eventDescription, String at) {
        super();
        this.eventDescription = eventDescription;
        this.at = at;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Event event = new Event(eventDescription, at);
        taskList.addToTaskList(event);
        ui.showAddTaskMessage(taskList, event);
    }
}
