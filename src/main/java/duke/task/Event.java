package duke.task;

import duke.ui.Ui;

/**
 * Class for a singular Event.
 */
public class Event extends Task {
    private String time;

    /**
     * Constructor for Event.
     * @param description - description of the event
     * @param time - time of the event
     */
    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String getSavedString() {
        return String.format("E | %s | %s | %s\n", this.getIsDone(), this.getDescription(), this.time);
    }

    @Override
    public String toString() {
        return String.format("[E] %s %s (at: %s)", Ui.checkbox(this.getIsDone()), this.getDescription(), this.time);
    }
}
