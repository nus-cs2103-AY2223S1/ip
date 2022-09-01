package duke.commands;

import duke.common.Messages;
import duke.exceptions.UnableToSaveException;
import duke.storage.StorageFile;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Adds a new event to the task list.
 */
public class EventCommand extends Command {

    public static final String COMMAND_WORD = "event";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Creates a new Event.\n"
            + "\tEx. " + COMMAND_WORD;

    private final String description;
    private final String eventDate;

    /**
     * Constructor for EventCommand.
     *
     * @param description description of the new event
     * @param eventDate time frame of the event (date, time, etc.)
     */
    public EventCommand(String description, String eventDate) {
        this.description = description;
        this.eventDate = eventDate;
    }

//    @Override
//    public void execute(TaskList taskList, Ui ui, StorageFile storage) throws UnableToSaveException {
//        taskList.addEventTask(description, eventDate);
//        storage.saveList(taskList);
//    }

    @Override
    public String execute(TaskList taskList, Ui ui, StorageFile storage) throws UnableToSaveException {
        taskList.addEventTask(description, eventDate);
        storage.saveList(taskList);
        return Messages.MESSAGE_TASK_ADDED;
    }
}