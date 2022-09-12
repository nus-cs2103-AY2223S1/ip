package kirby.commands;

import java.io.IOException;

import kirby.Storage;
import kirby.TaskList;
import kirby.exceptions.KirbyMissingArgumentException;
import kirby.tasks.Event;
import kirby.ui.Ui;

/**
 * EventCommand class handles the command to create an Event task.
 */
public class EventCommand extends Command {
    private final Event event;

    /**
     * Constructor for the class EventCommand.
     *
     * @param arguments Arguments of a command.
     */
    public EventCommand(String[] arguments) throws KirbyMissingArgumentException {
        String taskName = null;
        String time = null;
        taskName = arguments[0];
        time = arguments[1];
        if (taskName == null || time == null) {
            throw new KirbyMissingArgumentException("event");
        }
        this.event = new Event(taskName, time);
    }

    /**
     * {@inheritDoc}
     * Creates an Event task if arguments are valid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(this.event);
        try {
            storage.writeTask(tasks.getList());
        } catch (IOException e) {
            return (e.getMessage());
        }
        return tasks.addTaskString(event);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

