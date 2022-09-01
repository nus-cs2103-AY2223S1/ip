package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.Event;

public class EventCommand extends Command {
    private final Event event;

    public EventCommand(Event event) {
        super(false);
        this.event = event;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        taskList.addTask(event);
        storage.overwriteFile(taskList.toStorageString());
        return Command.wrapper.getAddTaskResponse(event.toString(), taskList.getNumOfTask());
    }
}
