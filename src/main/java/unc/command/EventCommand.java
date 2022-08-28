package unc.command;

import unc.*;
import unc.task.Event;

/**
 * Command that adds a new Event to list when executed.
 */
public class EventCommand extends Command{
    private final String description;

    /**
     * Constructor.
     *
     * @param input Description part of input following "event".
     */
    public EventCommand(String input) {
        this.description = input;
    }

    /**
     * Adds a new Event to list.
     * Saves updated list.
     *
     * @param taskList List to be operated on.
     * @param ui UI to print message.
     * @param storage Storage to save updated list.
     * @throws UncException If error occurs when creating new Event.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws UncException {
        String[] phrases = description.split(" /at ", 2);
        Event newEvent = new Event(phrases[0], phrases[1]);
        taskList.add(newEvent);
        ui.addEvent(taskList, newEvent);
        storage.save(taskList);
    }

    /**
     * {@inheritDoc}
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
