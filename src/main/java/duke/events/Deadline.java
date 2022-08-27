package duke.events;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>Class Deadline is a concrete class that extends the Task class.</p>
 * <p>This class is used to create a task with a deadline.</p>
 * <p>This class is a concrete class because it has an implementation.</p>
 */
public class Deadline extends Task{

    private String taskName;
    private LocalDateTime date;

    /**
     * Constructor for Deadline.
     * @param taskName the name of the task.
     * @param date the date and time of the task.
     */
    public Deadline(String taskName, LocalDateTime date) {
        super(taskName);
        this.taskName = taskName;
        this.date = date;
    }

    /**
     * Method that returns a String showing the date and time of the task.
     * @return a String showing the date and time and completion status of the task.
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by:" + date.format(DateTimeFormatter.ofPattern(" hh:mm a 'on' dd/MM/yyyy")) + ")";
    }

    /**
     * Method that returns the date and time of the task in a special format for creating a save file.
     * @return a String showing the date and time and completion status of the task.
     */
    @Override
    public String getSaveData() {
        return " D" + " | " + super.isDone() + " | " + taskName + " | " + date;
    }
}
