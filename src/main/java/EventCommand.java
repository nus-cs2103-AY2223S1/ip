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
     * @param display the display object that governs what response is returned to the user
     * @param storage the storage object to save tasks to hard drive
     * @return the related messages after an event task is added to KKBot
     * @throws StorageException if there is an error reading from/writing to hard drive
     */
    public String execute(TaskList tasks, Display display, Storage storage) throws StorageException {
        Event task = new Event(description, date);
        tasks.addTask(task);
        storage.save(tasks);
        return display.showTaskAddition(task)
                + display.showNumberOfTasks(tasks.getNumberOfTasks());
    }
}
