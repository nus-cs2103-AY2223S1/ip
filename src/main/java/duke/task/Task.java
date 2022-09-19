package duke.task;
import java.time.LocalDate;

/**
 * Class which manages tasks.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;
    protected LocalDate dateAndTime;

    /**
     * Creates an instance of task.
     * @param desc description of task
     */
    public Task(String desc) {
        this.description = desc;
        this.isDone = false;
        this.dateAndTime = null;
    }

    public String getTask() {
        String mark = " ";
        if (this.isDone) {
            mark = "X";
        }
        return "[" + type + "][" + mark + "]" + this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }
}
