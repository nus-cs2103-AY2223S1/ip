package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String timeQualifier;
    protected LocalDate timeDescription;
    protected String timeDescriptionInput;

    Event(String description, String timeQualifier, String timeDescription) {
        super(description);
        this.timeQualifier = timeQualifier;
        this.timeDescription = LocalDate.parse(timeDescription);
        this.timeDescriptionInput = timeDescription;
    }

    @Override
    public String getTaskTypeIcon() {
        return "E";
    }

    @Override
    public String toWrite() {
        return getTaskTypeIcon() + "," + (super.isDone ? "1," : "0,") + super.description + "," + timeQualifier + "," + timeDescriptionInput + "\n";
    }

    @Override
    public String toString() {
        return "[" + getTaskTypeIcon() + "]" + super.toString() + " (" + timeQualifier + ": " + timeDescription.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
