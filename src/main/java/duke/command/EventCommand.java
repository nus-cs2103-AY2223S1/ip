package duke.command;

import duke.task.Event;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to add an event task to the task list.
 */
public class EventCommand extends Command {

    public static final String COMMAND_WORD = "event";

    private String description;
    private String duration;

    /**
     * Constructor for the EventCommand Object.
     * @param description The description of the event.
     * @param duration The duration of the event.
     */
    public EventCommand(String description, String duration) {
        this.description = description;
        this.duration = duration;
    }

    /**
     * Creates an event task and add it into the task list and display a message
     * that the event task has been added.
     * @param task The TaskList object of the chatbot.
     * @param ui The Ui object of the chatbot.
     * @param storage The storage object of the chatbot.
     */
    @Override
    public void execute(TaskList task, Ui ui, Storage storage) {
        Event currEvent = new Event(this.description, this.duration);
        task.addTask(currEvent);
        ui.displayAddTask(currEvent);
        ui.displayNumOfTasks(task.getTaskSize());
        ui.displaySeparator();
    }

    /**
     * Return true if the command is exit command.
     * @return Return true if the command is an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
