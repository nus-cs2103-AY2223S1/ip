public class Event extends Task {
    private String time;

    /**
     * Constructor for the Event task.
     *
     * @param name Input name of the task.
     */
    public Event(String name) {
        super(name);
        String[] split = name.split(" /at ");
        this.name = split[0];
        this.time = split.length < 2 ? "You didn't add a time!" : split[1];
    }

    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Returns a string representation of the Event task.
     *
     * @return String representation of the Event task.
     */
    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)", getStatus(), name, time);
    }

    @Override
    public String changeFormat() {
        return String.format("E | %s | %s | %s", getStatus(), name, this.time);
    }
}
