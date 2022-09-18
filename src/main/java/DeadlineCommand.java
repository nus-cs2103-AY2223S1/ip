/**
 * Class representing the command for KKBot when user inputs a deadline task
 *
 * @author AkkFiros
 */

public class DeadlineCommand extends Command {
    public static final String KEYWORD = "deadline";
    public static final String DATE_INPUT = " /by ";
    private String description;
    private String date;

    /**
     * Constructor for a DeadlineCommand
     * @param description the description of the deadline task
     * @param date the deadline date of the task
     */
    public DeadlineCommand(String description, String date) {
        super();
        this.description = description;
        this.date = date;
    }

    /**
     * Returns a set of actions done by KKBot when user inputs "deadline".
     * @param tasks the list of tasks stored by KKBot
     * @param display the display object that governs what response is returned to the user
     * @param storage the storage object to save tasks to hard drive
     * @return the related messages after a deadline task is added to KKBot
     * @throws StorageException if there is an error reading from/writing to hard drive
     */
    public String execute(TaskList tasks, Display display, Storage storage) throws StorageException {
        Deadline task = new Deadline(description, date);
        tasks.addTask(task);
        storage.save(tasks);
        return display.showTaskAddition(task)
                + display.showNumberOfTasks(tasks.getNumberOfTasks());
    }
}
