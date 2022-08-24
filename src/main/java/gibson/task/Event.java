package gibson.task;

public class Event extends Task {
    private String startEnd;

    /**
     * Constructs an Event object that is represented by a task
     * and a String that represent the time of the task.
     * @param taskString the task
     * @param startEnd the time of the task
     */
    public Event(String taskString, String startEnd) {
        super(taskString);
        if (startEnd.isBlank()) {
            throw new IllegalArgumentException("Time of event cannot be blank.");
        }
        this.startEnd = startEnd;
    }

    @Override
    public char getChar() {
        return 'E';
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + this.startEnd + ")";
    }
}
