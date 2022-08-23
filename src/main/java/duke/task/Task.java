package duke.task;

import duke.core.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * A class that represents a task in the task list.
 */
public abstract class Task {
    protected String text = "";
    protected LocalDate details;
    protected boolean complete = false;

    public void setText(String text) {
        this.text = text;
    }

    public void setDetails(LocalDate details) {
        this.details = details;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public DukeException invalidParameterError() {
        return new DukeException("Invalid parameters!");
    }

    @Override
    public String toString() {
        return "[" + (complete ? "X" : " ") + "] " + text;
    }

    protected String getFormattedDetails() {
        return details.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
    }
}