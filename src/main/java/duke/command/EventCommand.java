package duke.command;

import duke.storage.Storage;
import duke.task.Event;
import duke.task.TaskList;

/**
 * The EventCommand class represents the command of adding a new event to Duke's task list.
 */
public class EventCommand extends Command {
    private final Event event;

    /**
     * Initializes an instance of EventCommand.
     *
     * @param event The event to be added to the task list.
     */
    public EventCommand(Event event) {
        super(false);
        this.event = event;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        taskList.addTask(event);
        storage.overwriteFile(taskList.toStorageString());
        return Command.WRAPPER.getAddTaskResponse(event.toString(), taskList.getNumOfTask());
    }
}
