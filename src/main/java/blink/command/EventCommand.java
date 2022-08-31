package blink.command;

import blink.BlinkException;
import blink.Storage;
import blink.TaskList;
import blink.Ui;
import blink.task.Events;

/**
 * Event command to create a new event task.
 */
public class EventCommand extends Command {

    private String desc;
    private String date;

    /**
     * Constructor for the EventCommand which splits the input
     * to get the Event description and date information.
     *
     * @param input Information of the Event object
     * @throws BlinkException Thrown if there is missing information
     * of Event object.
     */
    public EventCommand(String input) {
        String[] info = input.split("/at");
        if (info.length != 2) {
            throw new BlinkException("Error in command for event");
        }
        this.desc = info[0];
        this.date = info[1];
    }

    /**
     * Causes for Event object to be created in TaskList, then Ui will
     * display information of the Event object which is then saved in
     * Storage.
     *
     * @param tasks TaskList object of current Blink object
     * @param ui Ui object of current Blink object
     * @param storage Storage object of current Blink object
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Events event = tasks.addEvent(this.desc, this.date);
        storage.save(tasks);
        return ui.showAddTask(tasks, event);
    }

    /**
     * Event command will not end the program.
     *
     * @return False
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
