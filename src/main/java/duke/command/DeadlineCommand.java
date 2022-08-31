package duke.command;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskList;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Command to add a deadline task to the task list.
 */
public class DeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";

    private String description;
    private LocalDate localDate;
    private LocalTime localTime;

    /**
     * Constructor for the DeadlineCommand Object.
     * @param description The description of the deadline.
     * @param localDate The date of the deadline.
     * @param localTime The time of the deadline.
     */
    public DeadlineCommand(String description, LocalDate localDate, LocalTime localTime) {
        this.description = description;
        this.localDate = localDate;
        this.localTime = localTime;
    }

    /**
     * Creates a deadline task and add it into the task list and return a message
     * that the deadline task has been added.
     * @param task The TaskList object of the chatbot.
     * @param storage The storage object of the chatbot.
     * @return The message that the deadline has been added and the number of current tasks.
     */
    @Override
    public String execute(TaskList task, Storage storage) {
        Deadline currDeadline = new Deadline(this.description, this.localDate, this.localTime);
        task.addTask(currDeadline);
        return "Got it. I've added this task:\n " + currDeadline.taskInfo()
                + "\nNow you have"  + task.getTaskSize() + " tasks in the list.";
    }

}
