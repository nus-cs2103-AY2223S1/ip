public class Event extends Task {
    protected String time;
    /**
     * Construct Task with a fixed name.
     *
     * @param name The name of the task.
     */
    public Event(String name, String time) {
        super(name);
        this.time = time;
    }

    public Event(SaveLine line) {
        super(line);
        time = line.getValue("time");
    }

    /**
     * Shows the event name and status as a String.
     *
     * @return A String with the task name and status.
     */
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), time);
    }

    @Override
    public SaveLine toData() {
        SaveLine ret = super.toData();
        ret.setInfoType("event");
        ret.addNameData("time", time);
        return ret;
    }
}
