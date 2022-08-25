package duke.command;

import duke.task.Event;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class EventCommand extends Command {
    private String eventDescription;
    private String at;

    public EventCommand(String eventDescription, String at) {
        super();
        this.eventDescription = eventDescription;
        this.at = at;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Event event = new Event(eventDescription, at);
        taskList.addToTaskList(event);
        ui.showAddTaskMessage(taskList, event);
    }
}
