package justin.command;

import justin.*;
import justin.task.Deadline;
import justin.task.Task;

/**
 * Represents a command that is called to add a deadline
 * task in the TaskList, and save changes.
 * @author Justin Cheng.
 */
public class AddDeadlineCommand extends Command {
    private String description;
    private boolean isDone;
    private String by;
    private String time;
    private Deadline task;

    /**
     * Constructor for the AddDeadlineCommand class.
     * @param description This is the description of the task.
     * @param isDone This is the boolean value of whether the task is done.
     * @param by This is the date of which the task is due.
     * @param time This is the time of which the task is due.
     */
    public AddDeadlineCommand(String description, boolean isDone, String by, String time) {
        this.description = description;
        this.isDone = isDone;
        this.by = by;
        this.time = time;
    }

    /**
     * Second constructor for the AddDeadlineCommand class.
     * @param description This is the description of the task.
     * @param msg This is the array of strings which contain details
     *            such as its time and date in string format.
     */
    public AddDeadlineCommand(String description, String... msg) {
        this.description = description;
        this.isDone = false;
        this.by = msg[1];
        this.time = msg[2];
    }

    /**
     * Executes the task, which consists of
     * adding task to TaskList, sending messages through Ui,
     * and saving tasks to Storage.
     * @param list The TaskList to carry out operations.
     * @param ui The Ui to send outputs.
     * @param storage The Storage to save changes.
     * @return The String message of the Ui.
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        this.task = new Deadline(description, isDone, by, time);
        list.addTask(task);
        storage.save(list);
        return ui.getAddMessage() + ui.getSeparator() + task + ui.getSeparator() + ui.getCountMessage(list);
    }
}
