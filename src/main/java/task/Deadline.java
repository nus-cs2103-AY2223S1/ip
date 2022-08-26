package task;

import org.json.JSONObject;

/**
 * Class representing a Deadline task
 */
public class Deadline extends Task {
    private String by;

    /**
     * Creates new Deadline task
     * @param description Description for the deadline
     * @param by When to finish the task by
     */
    public Deadline(String description, String by, boolean isDone) {
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
        superObj.put("by", by);
        return superObj.toString();
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), by);
    }
}
