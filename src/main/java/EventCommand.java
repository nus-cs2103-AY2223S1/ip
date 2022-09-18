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
     * @return the related messages after an event task is added to KKBot
     */
    public String execute(TaskList tasks, Display display) {
        Event task = new Event(description, date);
        tasks.addTask(task);
        return display.showTaskAddition(task)
                + display.showNumberOfTasks(tasks.getNumberOfTasks());
    }
}
