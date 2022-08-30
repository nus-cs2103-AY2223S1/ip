package meowmeow.events;

/**
 * <p>Class Event is a concrete class that extends the Task class.</p>
 * <p>This class is used to create a task that is an event.</p>
 * <p>This class is a concrete class because it has an implementation.</p>
 */
public class Event extends Task {

    private String taskName;
    private String time;
    private boolean isDone = false;

    /**
     * Constructor for Event.
     * @param taskName the name of the task.
     * @param time the time of the task.
     */
    public Event(String taskName, String time) {
        super(taskName);
        this.time = time;
    }

    /**
     * Method that returns a String showing the time and completion status of the task.
     * @return a String showing the time and completion status of the task.
     */
    @Override
    public String toString() {
        return "[E] " + super.toString() + "(at:" + time + ")";
    }

    /**
     * Method that returns the time and completion status of the task in a special format for creating a save file.
     * @return a String showing the time and completion status of the task.
     */
    @Override
    public String getSaveData() {
        return " E" + " | " + super.isDone() + " | " + taskName + " | " + time;
    }
}
