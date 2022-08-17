package main.java;

// events have extra location feature
public class Events extends Task {
    protected String location;

    public Events(String taskName, String location) {
        super(taskName);
        this.location = location;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + location + ")";
    }
}
