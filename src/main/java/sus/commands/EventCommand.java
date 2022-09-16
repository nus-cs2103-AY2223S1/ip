package sus.commands;

import sus.SusException;
import sus.common.Messages;
import sus.storage.StorageFile;
import sus.task.Task;
import sus.task.TaskList;
import sus.ui.TextUi;

/**
 * Adds a new event to the task list.
 */
public class EventCommand extends Command {

    public static final String COMMAND_WORD = "event";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Creates a new Event.\n"
            + "\tEx.: " + COMMAND_WORD + " <description> /at <date/time>";

    private final String description;
    private final String timeFrame;

    /**
     * Constructor.
     *
     * @param description description of the event
     * @param timeFrame time frame of the event
     */
    public EventCommand(String description, String timeFrame) {
        this.description = description;
        this.timeFrame = timeFrame;
    }

    @Override
    public CommandResult execute(TaskList taskList, TextUi textUi, StorageFile storage) {
        try {
            Task task = taskList.addEvent(description, timeFrame);
            storage.save(taskList);
            return new CommandResult(Messages.MESSAGE_TASK_ADDED + "\n"
                    + task + "\n"
                    + String.format(Messages.MESSAGE_TASK_NUMBER, taskList.getNumberOfTasks()));
        } catch (SusException e) {
            return new CommandResult(e.getMessage());
        }
    }
}
