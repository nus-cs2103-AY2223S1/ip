package kkbot.commands;

import kkbot.storage.Storage;
import kkbot.storage.exceptions.StorageException;
import kkbot.tasks.Event;
import kkbot.tasklist.TaskList;
import kkbot.ui.Ui;

/**
 * Class representing the command for KKBot when user inputs an event task
 *
 * @author AkkFiros
 */

public class EventCommand extends Command {
    public static final String KEYWORD = "event";
    public static final String DATE_INPUT = " /at ";
    private String description;
    private String date;

    /**
     * Constructor for a EventCommand
     * @param description the description of the event task
     * @param date the date of the task
     */
    public EventCommand(String description, String date) {
        super();
        this.description = description;
        this.date = date;
    }

    /**
     * Returns a set of actions done by KKBot when user inputs "event".
     * @param tasks the list of tasks stored by KKBot
     * @param ui the ui object that governs what response is returned to the user
     * @param storage the storage object to save tasks to hard drive
     * @return the related messages after an event task is added to KKBot
     * @throws StorageException if there is an error reading from/writing to hard drive
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws StorageException {
        Event task = new Event(description, date);
        tasks.addTask(task);
        storage.save(tasks);
        return ui.showTaskAddition(task)
                + ui.showNumberOfTasks(tasks.getNumberOfTasks());
    }
}
