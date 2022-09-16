package unc.command;

import unc.Storage;
import unc.TaskList;
import unc.Ui;
import unc.UncException;
import unc.task.Event;

/**
 * Command that adds a new Event to list when executed.
 */
public class EventCommand extends Command {
    private final String description;

    /**
     * Constructor.
     *
     * @param input Description part of input following "event".
     */
    public EventCommand(String input) {
        assert input != null : "An event task shouldn't be created with null description.";
        this.description = input;
    }

    /**
     * Adds a new Event to list.
     * Saves updated list.
     *
     * @param taskList List to be operated on.
     * @param ui UI to print message.
     * @param storage Storage to save updated list.
     * @return the message to be shown by UI
     * @throws UncException If error occurs when creating new Event.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws UncException {
        String[] phrases = description.split(" /at ", 2);
        Event newEvent = new Event(phrases[0], phrases[1]);
        taskList.add(newEvent);
        storage.save(taskList);
        return ui.addEvent(taskList, newEvent);
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
