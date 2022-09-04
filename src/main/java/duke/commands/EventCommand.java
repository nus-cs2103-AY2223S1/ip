package duke.commands;

import duke.storage.Storage;
import duke.task.Event;
import duke.task.TaskList;

/**
 * Represents a command to add a new Event task.
 */
public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";

    private Event ev;

    /**
     * Constructs an EventCommand with the Event task
     * to be added.
     *
     * @param ev The Event task to be added.
     */
    public EventCommand(Event ev) {
        this.ev = ev;
    }

    /**
     * {@inheritDoc}
     * This command adds the event to the task list.
     *
     * @param tasks Contains the task list.
     * @param storage Storage to save and load tasks from a local file.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return tasks.add(ev);
    }
}
