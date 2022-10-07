package pixel.task;

/**
 * Represents a Task
 * Has three types, Event, Deadline and ToDo
 * Users can set deadlines to tasks
 * Tasks can be marked and unmarked as done
 * Tasks can be deleted
 */
public class Task {

    protected final String due; // can be accessed by subclasses
    protected final String commandWord;
    private final String description;
    private boolean isDone = false;

    /**
     * Constructor for a new Task object -- will only be called through its subclasses
     *
     * @param description description of the task
     * @param due due day/ date and time of the task
     * @param commandWord "at" or "by"
     */
    public Task(String description, String due, String commandWord) {
        this.description = description;
        this.due = due;
        this.commandWord = commandWord;
    }

    /**
     * Marks task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks task as not done
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Retrieves description of task
     *
     * @return Description string of task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Retrieves status icon of task
     *
     * @return "X" if task is done, " " if otherwise
     */
    private String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Converts the task to desired format before written to file
     *
     * @return string representation of task to be saved to file
     */
    public String formatTaskBeforeSave() {
        String isTaskDone = this.isDone ? "Done" : "Not Done";
        String tag = "";

        if (this instanceof Event) {
            tag = Event.TAG;
            assert tag == "E" : "event tag should be E";
        } else if (this instanceof Deadline) {
            tag = Deadline.TAG;
            assert tag == "D" : "deadline tag should be D";
        } else if (this instanceof ToDo) {
            tag = ToDo.TAG;
            assert tag == "T" : "todo tag should be T";
        }

        String taskToString = tag + " ;;; " + isTaskDone + " ;;; "
            + this.description + " ;;; " + this.commandWord + " ;;; " + this.due;
        return taskToString;
    }

    /**
     * toString method of Task
     *
     * @return String representation of the Task object
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

}

