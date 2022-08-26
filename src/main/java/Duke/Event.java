package Duke;

import java.time.LocalDate;

public class Event extends Task{
    protected LocalDate at;

    /**
     * Constructs an instance of Deadline
     *
     * @param description Description String
     * @param at Date String
     */
    public Event(String description, String at) {
        super(description);
        this.at = DateTimeConverter.formatDate(at);
    }

    /** @inheritdoc */
    @Override
    public String changeFormat() {
        String done = isDone ? "1" : "0";
        return "E | " + done + " | " + this.description + " | " + this.at;
    }

    /** @inheritdoc */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + DateTimeConverter.formatString(at) + ")";
    }
}
