package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.JSONObject;

/**
 * Class representing a Deadline task
 */
public class Deadline extends Task {
    protected static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy kkmm");
    private LocalDateTime by;

    /**
     * Creates new Deadline task
     * @param description Description for the deadline
     * @param by When to finish the task by
     */
    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, TaskType.DEADLINE, isDone);
        this.by = by;
    }
    @Override
    protected String getTypeIndicator() {
        return "D";
    }

    @Override
    protected String serialize() {
        JSONObject superObj = super.toJsonObject();
        superObj.put("by", by.format(DATE_FORMATTER));
        return superObj.toString();
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), by.format(DATE_FORMATTER));
    }
}
