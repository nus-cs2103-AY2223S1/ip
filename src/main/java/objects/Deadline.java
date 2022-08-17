package objects;

import objects.Task;

public class Deadline extends Task {
    private String endDateTime;

    public Deadline(String name, String endDateTime) {
        super(name);
        this.endDateTime = endDateTime;
    }

    public String getDateTime() {
        return this.endDateTime;
    }

    @Override
    public String toString() {
        return "[D]" + getStatus() + " " + getName() + "(by: " + getDateTime() + ")";
    }
}
